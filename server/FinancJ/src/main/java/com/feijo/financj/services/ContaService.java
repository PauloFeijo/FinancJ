package com.feijo.financj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Conta;
import com.feijo.financj.repositories.ContaRepository;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
@Primary
public class ContaService {

	@Autowired
	ContaRepository repo;

	public Conta find(Integer id) {

		Conta obj = repo.findById(id).orElse(null);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName());
		}
		return obj;
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

	protected void updateData(Conta newObj, Conta obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setNumero(obj.getNumero());
		newObj.setSaldo(obj.getSaldo());
	}
	
	public void processarSaldo(Integer contaId) {

		Conta conta = find(contaId);

		Double receitas = repo.somaReceitas(contaId);
		Double despesas = repo.somaDespesas(contaId);

		conta.setSaldo(receitas - despesas);
	}

}
