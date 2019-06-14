package com.feijo.financj;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feijo.financj.domain.CartaoCredito;
import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.Usuario;
import com.feijo.financj.domain.DTO.CategoriaDTO;
import com.feijo.financj.domain.DTO.MovimentacaoDTO;
import com.feijo.financj.domain.DTO.PagarReceberDTO;
import com.feijo.financj.domain.DTO.TransferenciaDTO;
import com.feijo.financj.domain.enums.Perfil;
import com.feijo.financj.services.CartaoCreditoService;
import com.feijo.financj.services.CategoriaService;
import com.feijo.financj.services.ContaService;
import com.feijo.financj.services.MovimentacaoService;
import com.feijo.financj.services.PagarReceberService;
import com.feijo.financj.services.TransferenciaService;
import com.feijo.financj.services.UsuarioService;

@SpringBootApplication
public class FinancJApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService userServ;
	
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
		
		Usuario user = new Usuario("teste", "teste", "teste@teste.com", "Usuário de teste");
		user.addPerfil(Perfil.ADMIN);
		userServ.insert(user);
		userServ.usuarioLogadoFake = "teste";
		
		contaServ.insert(new Conta(null, "Conta Corrente", "1234", 0.0));
		cartaoServ.insert(new CartaoCredito(null, "Cartão Nubank", "45654568465464", 0.0, df.parse("03/06/2019"), df.parse("13/06/2019"), 10, 6000.0));

		catServ.insert(new CategoriaDTO(null, "Despesas", "D", null));
		catServ.insert(new CategoriaDTO(null, "Casa", "D", 1));
		catServ.insert(new CategoriaDTO(null, "Supermercado", "D", 2));
		catServ.insert(new CategoriaDTO(null, "Receitas", "R", null));

		Date data = dhf.parse("01/06/2019 14:34:21");
		movServ.insert(new MovimentacaoDTO(null, 2, 3, "Compra no supermercado", data, 800.00, "D"));
		movServ.insert(new MovimentacaoDTO(null, 1, 4, "Salário", data, 1000.00, "R"));

		Parcela parc1 = new Parcela(null, 1, df.parse("01/06/2019"), 100.0, 100.0);
		Parcela parc2 = new Parcela(null, 1, df.parse("01/07/2019"), 100.0, 0.0);
		Set<Parcela> parcelas = new HashSet<>();
		parcelas.addAll(Arrays.asList(parc1, parc2));
		PagarReceberDTO pagRecDto = new PagarReceberDTO(null, "Roupas", 1, 200.0, parc1.getVencimento(), 3, 2, parcelas);
		pagRecService.insert(pagRecDto);

		transfServ.insert(new TransferenciaDTO(null, 1, 2, dhf.parse("02/06/2019 10:30:55"), 100.0));

		//cartaoServ.fecharFaturas();
		
		//cartaoServ.pagarFatura(new PagarFaturaDTO(2, 1, 800.0, df.parse("06/06/2019")));
		
		// teste usuario logado
		userServ.insert(new Usuario("paulo", "paulo", "paulo@teste.com", "Paulo Ricardo"));
		userServ.usuarioLogadoFake = "paulo";
		Categoria cat = catServ.insert(new CategoriaDTO(null, "Despesas", "D", null));
		Conta conta = contaServ.insert(new Conta(null, "Conta Corrente Paulo", "56789", 0.0));
		Conta conta2 = contaServ.insert(new Conta(null, "Conta Poupança Paulo", "6545", 0.0));
		movServ.insert(new MovimentacaoDTO(null, conta.getId(), cat.getId(), "Compra no supermercado", data, 800.00, "D"));
		pagRecService.insert(new PagarReceberDTO(null, "Sofá", 1, 200.0, parc1.getVencimento(), cat.getId(), conta.getId(), parcelas));
		transfServ.insert(new TransferenciaDTO(null, conta.getId(), conta2.getId(), dhf.parse("14/06/2019 00:30:00"), 100.0));

	}

}
