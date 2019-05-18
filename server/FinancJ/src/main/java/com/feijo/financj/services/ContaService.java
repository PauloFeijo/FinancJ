package com.feijo.financj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Conta;
import com.feijo.financj.repositories.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository repo;

	public Conta find(Integer id) {
		Conta conta = repo.findById(id).orElse(null);
		return conta;
	}

	public List<Conta> findAll() {
		return repo.findAll();
	}

	public Conta insert(Conta obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Conta update(Conta obj) {
		Conta newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	private void updateData(Conta newObj, Conta obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setNumero(obj.getNumero());
		newObj.setSaldo(obj.getSaldo());
	}
}
