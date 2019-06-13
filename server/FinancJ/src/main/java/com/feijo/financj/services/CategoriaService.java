package com.feijo.financj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.DTO.CategoriaDTO;
import com.feijo.financj.domain.enums.Tipo;
import com.feijo.financj.repositories.CategoriaRepository;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findById(id).orElse(null);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}		
		return obj;
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Categoria insert(CategoriaDTO objDto) {
		Categoria obj = new Categoria();
		updateData(obj, objDto);
		return repo.save(obj);
	}

	public Categoria update(CategoriaDTO objDto) {
		Categoria obj = find(objDto.getId());
		updateData(obj, objDto);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	private void updateData(Categoria obj, CategoriaDTO objDto) {
		obj.setDescricao(objDto.getDescricao());
		obj.setTipo(Tipo.toEnum(objDto.getTipo()));
		
		if (objDto.getCategoriaPaiId() != null) {
			Categoria catPai = find(objDto.getCategoriaPaiId());
			obj.setCategoriaPai(catPai);
		} else {
			obj.setCategoriaPai(null);
		}
	}
}
