package com.feijo.financj.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.Transferencia;
import com.feijo.financj.domain.DTO.MovimentacaoDTO;
import com.feijo.financj.domain.enums.Tipo;
import com.feijo.financj.repositories.MovimentacaoRepository;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentacaoService {
	
	private Set<Conta> contasProcessarSaldo = new HashSet<>();

	@Autowired
	MovimentacaoRepository repo;

	@Autowired
	ContaService contaServ;

	@Autowired
	CategoriaService catServ;

	public Movimentacao find(Integer id) {
		
		Movimentacao obj = repo.findById(id).orElse(null);
				
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Movimentacao.class.getName());
		}

		return obj;
	}
	
	public MovimentacaoDTO findDTO(Integer id) {
		return toDTO(find(id));
	}

	public List<MovimentacaoDTO> findAll() {
		return toListDTO(repo.findAll());
	}

	@Transactional
	public Movimentacao insert(MovimentacaoDTO objDto) {
		objDto.setId(null);
		return insertOrUpdate(objDto);
	}

	@Transactional
	public Movimentacao update(MovimentacaoDTO objDto) {
		return insertOrUpdate(objDto);
	}

	@Transactional
	public void delete(Integer id) {
		Movimentacao obj = find(id);
		contasProcessarSaldo.add(obj.getConta());
		repo.deleteById(id);
		processarSaldoContas();
	}
	
	@Transactional
	public Movimentacao insertUpdateByParcela(Parcela parc) {
		
		Movimentacao mov = repo.findByParcela(parc);
		
		if (mov == null) {
			mov = new Movimentacao();
			mov.setData(new Date());
		} else {
			if (mov.getConta() != parc.getPagarReceber().getConta()) {
				contasProcessarSaldo.add(mov.getConta());
			}
		}
		
		mov.setCategoria(parc.getPagarReceber().getCategoria());
		mov.setConta(parc.getPagarReceber().getConta());
		mov.setDescricao(montarDescricaoMovimentacaoParcela(parc));
		mov.setTipo(parc.getPagarReceber().getCategoria().getTipo());
		mov.setValor(parc.getValorPago());
		mov.setParcela(parc);
		
		contasProcessarSaldo.add(mov.getConta());
		
		mov = repo.save(mov);
		
		processarSaldoContas();
		
		return mov;
	}
	
	@Transactional
	public void deleteByParcela(Parcela parc) {
		Movimentacao mov = repo.findByParcela(parc);
		
		if (mov == null) return;
		
		repo.delete(mov);
		
		contasProcessarSaldo.add(parc.getPagarReceber().getConta());
		
		processarSaldoContas();
	}
	
	@Transactional
	public void insertUpdateByTransferencia(Transferencia transf) {
		Movimentacao entrada;
		Movimentacao saida;
		
		if (transf.getId() != null) {
			entrada = find(transf.getEntrada().getId());
			saida = find(transf.getSaida().getId());
		} else {
			entrada = new Movimentacao();
			saida = new Movimentacao();
		}
		
		saida.setConta(transf.getOrigem());
		saida.setData(transf.getData());
		saida.setDescricao("Transferência para a conta "+transf.getDestino().getDescricao());
		saida.setTipo(Tipo.DESPESA);
		saida.setValor(transf.getValor());
		
		entrada.setConta(transf.getDestino());
		entrada.setData(transf.getData());
		entrada.setDescricao("Transferência da conta "+transf.getOrigem().getDescricao());
		entrada.setTipo(Tipo.RECEITA);
		entrada.setValor(transf.getValor());
		
		repo.saveAll(Arrays.asList(saida, entrada));
		
		transf.setEntrada(entrada);
		transf.setSaida(saida);
		
		contasProcessarSaldo.addAll(Arrays.asList(transf.getOrigem(), transf.getDestino()));
		processarSaldoContas();
	}
	
	@Transactional
	public void deleteByTransferencia(Transferencia transf) {
		Movimentacao entrada = find(transf.getEntrada().getId());
		Movimentacao saida = find(transf.getSaida().getId());
		
		repo.deleteAll(Arrays.asList(entrada, saida));
		
		contasProcessarSaldo.addAll(Arrays.asList(transf.getOrigem(), transf.getDestino()));
		processarSaldoContas();
	}
	
	private String montarDescricaoMovimentacaoParcela(Parcela parc) {
		String descri = "";
		
		if (parc.getPagarReceber().getNumParcelas() > 1) {
			descri = parc.getNumParcela() + "/" + parc.getPagarReceber().getNumParcelas() + " ";
		}
		
		return descri + parc.getPagarReceber().getDescricao();  
	}
	
	@Transactional
	private Movimentacao insertOrUpdate(MovimentacaoDTO objDto) {
		Movimentacao obj = fromDTO(objDto);
		obj = repo.save(obj);
		processarSaldoContas();
		return obj;
	}

	@Transactional
	private void processarSaldoContas() {
		for (Conta conta : contasProcessarSaldo) {
			contaServ.processarSaldo(conta.getId());
		}
		contasProcessarSaldo.clear();
	}
	
	private MovimentacaoDTO toDTO(Movimentacao obj) {

		if (obj == null) {
			return null;
		}

		MovimentacaoDTO objDto = new MovimentacaoDTO();

		if (obj.getId() != null) {
			objDto.setId(obj.getId());
		}
		
		if (obj.getCategoria() != null) {
			objDto.setCategoriaId(obj.getCategoria().getId());
			objDto.setCategoriaDescricao(obj.getCategoria().getDescricao());
		}
		
		objDto.setContaId(obj.getConta().getId());
		objDto.setContaDescricao(obj.getConta().getDescricao());		
		objDto.setDescricao(obj.getDescricao());
		objDto.setData(obj.getData());
		objDto.setValor(obj.getValor());
		objDto.setTipo(obj.getTipo().getFlag());

		return objDto;
	}

	private Movimentacao fromDTO(MovimentacaoDTO objDto) {
		if (objDto == null) {
			return null;
		}

		Conta conta = contaServ.find(objDto.getContaId());
		contasProcessarSaldo.add(conta);
		
		Movimentacao obj;
		
		if (objDto.getId() != null) {
			obj = find(objDto.getId());
			obj.setId(objDto.getId());
			contasProcessarSaldo.add(obj.getConta());
		} else {
			obj = new Movimentacao();
		}
		
		if (objDto.getCategoriaId() != null) {
			Categoria categoria = catServ.find(objDto.getCategoriaId());
			obj.setCategoria(categoria);
		}

		obj.setConta(conta);
		obj.setDescricao(objDto.getDescricao());
		obj.setData(objDto.getData());
		obj.setValor(objDto.getValor());
		obj.setTipo(Tipo.toEnum(objDto.getTipo()));

		return obj;
	}

	private List<MovimentacaoDTO> toListDTO(List<Movimentacao> list) {

		if (list == null) {
			return null;
		}

		List<MovimentacaoDTO> listDto = new ArrayList<>();

		for (Movimentacao mov : list) {
			listDto.add(toDTO(mov));
		}

		return listDto;
	}

}
