package com.feijo.financj.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Movimentacao;
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
	
	private Movimentacao insertOrUpdate(MovimentacaoDTO objDto) {
		Movimentacao obj = fromDTO(objDto);
		obj = repo.save(obj);
		processarSaldoContas();
		return obj;
	}

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
		objDto.setContaId(obj.getConta().getId());
		objDto.setContaDescricao(obj.getConta().getDescricao());
		objDto.setCategoriaId(obj.getCategoria().getId());
		objDto.setCategoriaDescricao(obj.getCategoria().getDescricao());
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
		Categoria categoria = catServ.find(objDto.getCategoriaId());
		
		contasProcessarSaldo.add(conta);
		
		Movimentacao obj;
		
		if (objDto.getId() != null) {
			obj = find(objDto.getId());
			obj.setId(objDto.getId());
			contasProcessarSaldo.add(obj.getConta());
		} else {
			obj = new Movimentacao();
		}

		obj.setConta(conta);
		obj.setCategoria(categoria);
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

	private List<Movimentacao> fromListDTO(List<MovimentacaoDTO> listDto) {

		if (listDto == null) {
			return null;
		}

		List<Movimentacao> list = new ArrayList<>();

		for (MovimentacaoDTO movDto : listDto) {
			list.add(fromDTO(movDto));
		}

		return list;
	}

}
