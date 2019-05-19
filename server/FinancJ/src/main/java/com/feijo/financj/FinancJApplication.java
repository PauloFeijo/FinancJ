package com.feijo.financj;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.enums.Tipo;
import com.feijo.financj.repositories.CategoriaRepository;
import com.feijo.financj.repositories.ContaRepository;

@SpringBootApplication
public class FinancJApplication implements CommandLineRunner{
	
	@Autowired
	private ContaRepository contaRepo;
	
	@Autowired
	private CategoriaRepository catRepo;

	public static void main(String[] args) {
		SpringApplication.run(FinancJApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception  {
		
		Conta conta = new Conta(1, "Conta Corrente", "1234", 0.0);
		contaRepo.save(conta);
		
		Categoria cat1 = new Categoria(1,"Despesas", Tipo.DESPESA);
		Categoria cat2 = new Categoria(2,"Casa", Tipo.DESPESA);
		Categoria cat3 = new Categoria(3,"Supermercado", Tipo.DESPESA);
		
		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		cat1.getCategorias().addAll(Arrays.asList(cat2));
		cat2.setCategoriaPai(cat1);
		cat2.getCategorias().addAll(Arrays.asList(cat3));
		cat3.setCategoriaPai(cat2);
		
		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3));
		
	}

}
