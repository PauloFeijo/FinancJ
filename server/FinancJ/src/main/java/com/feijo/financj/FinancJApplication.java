package com.feijo.financj;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feijo.financj.domain.CartaoCredito;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.DTO.CategoriaDTO;
import com.feijo.financj.domain.DTO.MovimentacaoDTO;
import com.feijo.financj.domain.DTO.PagarReceberDTO;
import com.feijo.financj.domain.DTO.TransferenciaDTO;
import com.feijo.financj.services.CartaoCreditoService;
import com.feijo.financj.services.CategoriaService;
import com.feijo.financj.services.ContaService;
import com.feijo.financj.services.MovimentacaoService;
import com.feijo.financj.services.PagarReceberService;
import com.feijo.financj.services.TransferenciaService;

@SpringBootApplication
public class FinancJApplication implements CommandLineRunner {

	@Autowired
	private ContaService contaServ;

	@Autowired
	private CartaoCreditoService cartaoServ;

	@Autowired
	private CategoriaService catServ;

	@Autowired
	private MovimentacaoService movServ;

	@Autowired
	private PagarReceberService pagRecService;
	
	@Autowired
	private TransferenciaService transfServ;

	public static void main(String[] args) {
		SpringApplication.run(FinancJApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat dhf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dataHora = dhf.parse("01/01/2019 08:00:00");
		Date data = df.parse("01/01/2019");

		contaServ.insert(new Conta(1, "Conta Corrente", "1234", 0.0));
		cartaoServ.insert(new CartaoCredito(2, "Cartão Nubank", "45654568465464", 0.0, data, 0.0, 6000.0, 0.0));

		catServ.insert(new CategoriaDTO(1, "Despesas", "D", null));
		catServ.insert(new CategoriaDTO(2, "Casa", "D", 1));
		catServ.insert(new CategoriaDTO(3, "Supermercado", "D", 2));
		catServ.insert(new CategoriaDTO(4, "Receitas", "R", null));

		movServ.insert(new MovimentacaoDTO(null, 2, 3, "Compra no supermercado", dataHora, 800.00, "D"));
		movServ.insert(new MovimentacaoDTO(null, 1, 4, "Salário", dataHora, 1000.00, "R"));

		Parcela parc = new Parcela(null, 1, dataHora, 100.0, 100.0);
		Set<Parcela> parcelas = new HashSet<>();
		parcelas.add(parc);
		PagarReceberDTO pagRecDto = new PagarReceberDTO(null, "Roupas", 1, 100.0, parc.getVencimento(), 3, 2, parcelas);
		pagRecService.insert(pagRecDto);

		transfServ.insert(new TransferenciaDTO(1, 1, 2, dataHora, 100.0));

	}

}
