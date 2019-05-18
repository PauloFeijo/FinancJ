package com.feijo.financj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feijo.financj.domain.Conta;
import com.feijo.financj.repositories.ContaRepository;

@SpringBootApplication
public class FinancJApplication implements CommandLineRunner{
	
	@Autowired
	private ContaRepository contaRepo;

	public static void main(String[] args) {
		SpringApplication.run(FinancJApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception  {
		Conta conta = new Conta(1, "Conta Corrente", "1234", 0.0);
		contaRepo.save(conta);
	}

}
