package com.feijo.financj;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.hibernate.type.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feijo.financj.domain.CartaoCredito;
import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.enums.Tipo;
import com.feijo.financj.repositories.CategoriaRepository;
import com.feijo.financj.repositories.ContaRepository;
import com.feijo.financj.repositories.MovimentacaoRepository;

@SpringBootApplication
public class FinancJApplication implements CommandLineRunner{
	
	@Autowired
	private ContaRepository contaRepo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private MovimentacaoRepository movRepo;

	public static void main(String[] args) {
		SpringApplication.run(FinancJApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception  {
		
		SimpleDateFormat dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		Conta conta = new Conta(1, "Conta Corrente", "1234", 0.0);
		CartaoCredito cartao = new CartaoCredito(2, "Cartão Nubank", "45654568465464", 0.0, data.parse("01/01/2019"), 0.0, 6000.0);
		contaRepo.saveAll(Arrays.asList(conta, cartao));
		
		Categoria cat1 = new Categoria(1,"Despesas", Tipo.DESPESA);
		Categoria cat2 = new Categoria(2,"Casa", Tipo.DESPESA);
		Categoria cat3 = new Categoria(3,"Supermercado", Tipo.DESPESA);
		
		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		cat1.getCategorias().addAll(Arrays.asList(cat2));
		cat2.setCategoriaPai(cat1);
		cat2.getCategorias().addAll(Arrays.asList(cat3));
		cat3.setCategoriaPai(cat2);
		
		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Movimentacao mov = new Movimentacao(1, conta, cat3, "Compra no supermercado", dataHora.parse("01/01/2019 11:00:00"), 800.00, Tipo.DESPESA);
		
		movRepo.save(mov);
	}

}
