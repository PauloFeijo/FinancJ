package com.feijo.financj.services;

import org.springframework.stereotype.Service;

import com.feijo.financj.domain.CartaoCredito;
import com.feijo.financj.domain.Conta;

@Service
public class CartaoCreditoService extends ContaService{

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

}
