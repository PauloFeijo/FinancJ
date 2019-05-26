package com.feijo.financj.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.PagarReceber;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.DTO.PagarReceberDTO;
import com.feijo.financj.repositories.PagarReceberRepository;
import com.feijo.financj.repositories.ParcelaRepository;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class PagarReceberService {

	@Autowired
	PagarReceberRepository repo;
	
	@Autowired
	ParcelaRepository parcRepo;

	@Autowired
	ContaService contaServ;

	@Autowired
	CategoriaService catServ;
	
	public PagarReceber find(Integer id) {
		
		PagarReceber obj = repo.findById(id).orElse(null);
				
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + PagarReceber.class.getName());
		}

		return obj;
	}
	
	public PagarReceberDTO findDTO(Integer id) {
		return toDTO(find(id));
	}

	public List<PagarReceberDTO> findAll() {
		return toListDTO(repo.findAll());
	}

	@Transactional
	public PagarReceber insert(PagarReceberDTO objDto) {
		objDto.setId(null);
		return insertOrUpdate(objDto);
	}

	@Transactional
	public PagarReceber update(PagarReceberDTO objDto) {
		return insertOrUpdate(objDto);
	}

	@Transactional
	public void delete(Integer id) {
		
		PagarReceber obj = find(id);
		
		Integer contaId = obj.getConta().getId();
		
		repo.deleteById(id);
	}
	
	private void upatePagarReceberParcela(PagarReceber obj) {
		for (Parcela parc: obj.getParcelas()) {
			parc.setPagarReceber(obj);
		}
	}
	
	private PagarReceber insertOrUpdate(PagarReceberDTO objDto) {
		
		PagarReceber obj = fromDTO(objDto);
		
		obj = repo.save(obj);
		
		upatePagarReceberParcela(obj);
		
		parcRepo.saveAll(obj.getParcelas());
		
		return obj;
	}

	private PagarReceberDTO toDTO(PagarReceber obj) {

		if (obj == null) {
			return null;
		}

		PagarReceberDTO objDto = new PagarReceberDTO();

		if (obj.getId() != null) {
			objDto.setId(obj.getId());
		}
		
		objDto.setDescricao(obj.getDescricao());
		objDto.setNumParcelas(obj.getNumParcelas());
		objDto.setValorTotal(obj.getValorTotal());
		objDto.setVencimento(obj.getVencimento());
		objDto.setValorPago(obj.getValorPago());
		objDto.setCategoriaId(obj.getCategoria().getId());
		objDto.setCategoriaDescricao(obj.getCategoria().getDescricao());
		objDto.setContaId(obj.getConta().getId());
		objDto.setContaDescricao(obj.getConta().getDescricao());
		objDto.setParcelas(obj.getParcelas());

		return objDto;
	}

	private PagarReceber fromDTO(PagarReceberDTO objDto) {

		if (objDto == null) {
			return null;
		}
		
		PagarReceber obj;
		
		Conta conta = contaServ.find(objDto.getContaId());
		Categoria categoria = catServ.find(objDto.getCategoriaId());
		
		if (objDto.getId() != null) {
			obj = find(objDto.getId());
			obj.setId(objDto.getId());
		} else {
			obj = new PagarReceber();
		}
		
		obj.setDescricao(objDto.getDescricao());
		obj.setNumParcelas(objDto.getNumParcelas());
		obj.setValorTotal(objDto.getValorTotal());
		obj.setVencimento(objDto.getVencimento());
		obj.setValorPago(objDto.getValorPago());
		obj.setConta(conta);
		obj.setCategoria(categoria);
		obj.setParcelas(objDto.getParcelas());		

		return obj;
	}

	private List<PagarReceberDTO> toListDTO(List<PagarReceber> list) {

		if (list == null) {
			return null;
		}

		List<PagarReceberDTO> listDto = new ArrayList<>();

		for (PagarReceber mov : list) {
			listDto.add(toDTO(mov));
		}

		return listDto;
	}

	private List<PagarReceber> fromListDTO(List<PagarReceberDTO> listDto) {

		if (listDto == null) {
			return null;
		}

		List<PagarReceber> list = new ArrayList<>();

		for (PagarReceberDTO movDto : listDto) {
			list.add(fromDTO(movDto));
		}

		return list;
	}

}
