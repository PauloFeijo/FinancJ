package com.feijo.financj.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.DTO.MovimentacaoDTO;
import com.feijo.financj.domain.enums.Tipo;
import com.feijo.financj.repositories.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	MovimentacaoRepository repo;
	
	@Autowired
	ContaService contaServ;
	
	@Autowired
	CategoriaService catServ;	

	public Movimentacao find(Integer id) {
		Movimentacao movimentacao = repo.findById(id).orElse(null);
		return movimentacao;
	}

	public List<Movimentacao> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Movimentacao insert(MovimentacaoDTO objDto) {
		Movimentacao obj = new Movimentacao();
		updateData(obj, objDto);
		obj = repo.save(obj);
		contaServ.processarSaldo(obj.getConta().getId());
		return obj;
	}

	@Transactional
	public Movimentacao update(MovimentacaoDTO objDto) {
		Movimentacao obj = find(objDto.getId());
		updateData(obj, objDto);
		obj = repo.save(obj);
		contaServ.processarSaldo(obj.getConta().getId());
		return obj;
	}

	@Transactional
	public void delete(Integer id) {
		Movimentacao obj = find(id);
		Integer contaId = obj.getConta().getId();
		repo.deleteById(id);
		contaServ.processarSaldo(contaId);
	}

	private void updateData(Movimentacao obj, MovimentacaoDTO objDto) {
		
		obj.setDescricao(objDto.getDescricao());
		// problema com data
		//obj.setData(objDto.getData());		
		obj.setValor(objDto.getValor());
		obj.setTipo(Tipo.toEnum(objDto.getTipo()));
		
		Conta conta = contaServ.find(objDto.getConta_id());
		obj.setConta(conta);
		
		Categoria categoria = catServ.find(objDto.getCategoria_id());
		obj.setCategoria(categoria);

	}
	
}
