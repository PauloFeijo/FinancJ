package com.feijo.financj.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.CartaoCredito;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.DTO.PagarFaturaDTO;
import com.feijo.financj.domain.DTO.TransferenciaDTO;
import com.feijo.financj.repositories.ParcelaRepository;

@Service
public class CartaoCreditoService extends ContaService{
	
	@Autowired
	TransferenciaService tranfServ;
	
	@Autowired
	ParcelaRepository parcRepo;
	
	@Autowired
	MovimentacaoService movServ;

	public CartaoCredito insert(CartaoCredito obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public CartaoCredito update(CartaoCredito obj) {
		CartaoCredito newObj = (CartaoCredito) find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void updateData(CartaoCredito newObj, CartaoCredito obj) {
		newObj.setDataFatura(obj.getDataFatura());
		newObj.setDataVencimentoFatura(obj.getDataVencimentoFatura());
		newObj.setFaturaFechada(obj.getFaturaFechada());
		newObj.setValorLimite(obj.getValorLimite());
		updateData((Conta) newObj, (Conta) obj);
	}
	
	public void processarFaturaFutura(Integer cartaoId) {
		// faturaAtual é o saldo, e já é processado em contaService, ao ser incluida uma nova movimentação
		// faturaFechada é o processo de fechar fatura (ainda será implementado)
		// faturaFutura é a soma das parcelas em aberto
		CartaoCredito cartao = (CartaoCredito) find(cartaoId);
		Double faturaFutura = repo.somaFaturaFutura(cartaoId);
		cartao.setFaturaFutura(faturaFutura);
	}
	
	@Transactional
	public void fecharFaturas() {
		
		List<Conta> contas = findAll();
		
		for (Conta conta : contas) {
			if (! (conta instanceof CartaoCredito)) continue;
			
			fecharFatura((CartaoCredito) conta);
			
			repo.save(conta);
		}
	}
	
	@Transactional
	private void fecharFatura(CartaoCredito cartao) {
		
		Date dataInicio = new Date(0); 
		Date hoje = new Date();
		
		if (cartao.getDataFatura().after(hoje)) return;
		
		Date faturaAtual = cartao.getDataFatura();
		
		Date vencto = faturaAtual;
		Calendar c = Calendar.getInstance();
		c.setTime(vencto);
		c.add(Calendar.DATE, cartao.getDiasVencimentoFatura());
		vencto = c.getTime();
		
		Date proximaFatura = cartao.getDataFatura();
		c.setTime(proximaFatura);
		c.add(Calendar.MONTH, 1);
		proximaFatura = c.getTime();
			
		cartao.setDataFatura(proximaFatura);
		cartao.setDataVencimentoFatura(vencto);
		
		Double ReceitaPeriodo = repo.somaReceitasNoPeriodo(cartao.getId(), dataInicio, hoje);
		Double DespesaPeriodo = repo.somaDespesasNoPeriodo(cartao.getId(), dataInicio, hoje);
		
		cartao.setFaturaFechada(DespesaPeriodo - ReceitaPeriodo);
		
		// processar a fatura atual, devem ser baixadas as parcelas do mes atual
		Set<Parcela> parcelas = parcRepo.findEmAbertoByContaPeriodo(cartao.getId(), hoje, proximaFatura);
		
		for (Parcela parcela : parcelas) {			
			parcela.setValorPago(parcela.getValor());
			movServ.insertUpdateByParcela(parcela);
		}
	}
	
	@Transactional
	public void pagarFatura(PagarFaturaDTO obj) {
		TransferenciaDTO transf = new TransferenciaDTO(null, obj.getContaId(), obj.getCartaoId(), obj.getData(), obj.getValor());
		tranfServ.insert(transf);
		
		CartaoCredito cartao = (CartaoCredito) find(obj.getCartaoId());
		cartao.setFaturaFechada(cartao.getFaturaFechada() - obj.getValor());
		
		Date venctoFatura = cartao.getDataFatura();
		Calendar c = Calendar.getInstance();
		c.setTime(venctoFatura);
		c.add(Calendar.DATE, cartao.getDiasVencimentoFatura());
		venctoFatura = c.getTime();
				
		cartao.setDataVencimentoFatura(venctoFatura);
		
		processarFaturaFutura(obj.getCartaoId());
		
	}

}
