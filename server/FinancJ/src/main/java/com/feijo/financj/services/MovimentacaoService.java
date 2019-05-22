package com.feijo.financj.services;

import java.util.ArrayList;
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
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentacaoService {

	@Autowired
	MovimentacaoRepository repo;

	@Autowired
	ContaService contaServ;

	@Autowired
	CategoriaService catServ;

	public MovimentacaoDTO find(Integer id) {
		
		Movimentacao obj = repo.findById(id).orElse(null);
				
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Movimentacao.class.getName());
		}

		return toDTO(obj);
	}

	public List<MovimentacaoDTO> findAll() {
		return toListDTO(repo.findAll());
	}

	@Transactional
	public Movimentacao insert(MovimentacaoDTO objDto) {
		Movimentacao obj = fromDTO(objDto);
		obj = repo.save(obj);
		contaServ.processarSaldo(obj.getConta().getId());
		return obj;
	}

	@Transactional
	public Movimentacao update(MovimentacaoDTO objDto) {
		Movimentacao obj = fromDTO(find(objDto.getId()));
		obj = repo.save(obj);
		contaServ.processarSaldo(obj.getConta().getId());
		return obj;
	}

	@Transactional
	public void delete(Integer id) {
		Movimentacao obj = fromDTO(find(id));
		Integer contaId = obj.getConta().getId();
		repo.deleteById(id);
		contaServ.processarSaldo(contaId);
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

		Movimentacao obj = new Movimentacao();

		Conta conta = contaServ.find(objDto.getContaId());
		Categoria categoria = catServ.find(objDto.getCategoriaId());

		if (objDto.getId() != null) {
			obj.setId(objDto.getId());
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
