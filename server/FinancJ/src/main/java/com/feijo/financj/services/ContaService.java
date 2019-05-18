package com.feijo.financj.services;

import java.util.Optional;

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
}
