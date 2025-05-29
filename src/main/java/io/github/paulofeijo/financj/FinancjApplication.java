package io.github.paulofeijo.financj;

import io.github.paulofeijo.financj.entities.Account;
import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.enums.Type;
import io.github.paulofeijo.financj.repositories.AccountRepository;
import io.github.paulofeijo.financj.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class FinancjApplication {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinancjApplication.class, args);
	}

	@Bean
	public CommandLineRunner initCategories() {
		return args -> {

			var receitas = new Category();
			receitas.setDescription("Receitas");
			receitas.setType(Type.R);

			var despesas = new Category();
			despesas.setDescription("Despesas");
			despesas.setType(Type.E);

			categoryRepository.saveAll(Arrays.asList(receitas, despesas));

			var caixa = new Account();
			caixa.setDescription("Caixa");

			var corrente = new Account();
			corrente.setDescription("Corrente");
			corrente.setNumber("0001");

			accountRepository.saveAll(Arrays.asList(caixa, corrente));

		};
	}
}