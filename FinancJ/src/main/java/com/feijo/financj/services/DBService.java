package com.feijo.financj.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

@Service
public class DBService {

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

	public void instantiateTestDatabase() throws ParseException {

		SimpleDateFormat dhf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Usuario usu1 = new Usuario("teste", "teste", "teste@teste.com", "Usuário de teste");
		usu1.addPerfil(Perfil.ADMIN);
		userServ.insert(usu1);
		userServ.usuarioLogadoFake = usu1.getUsuario();

		Conta conta1 = contaServ.insert(new Conta(null, "Conta Corrente", "1234", 0.0));
		CartaoCredito cartao1 = cartaoServ.insert(new CartaoCredito(null, "Cartão Nubank", "45654568465464", 0.0, df.parse("08/06/2019"), df.parse("18/06/2019"), 10, 6000.0));

		catServ.insert(new CategoriaDTO(null, "Despesas", "D", null));
		catServ.insert(new CategoriaDTO(null, "Casa", "D", 1));
		Categoria catSup1 = catServ.insert(new CategoriaDTO(null, "Supermercado", "D", 2));
		Categoria catRec1 = catServ.insert(new CategoriaDTO(null, "Receitas", "R", null));

		movServ.insert(new MovimentacaoDTO(null, cartao1.getId(), catSup1.getId(), "Compra no supermercado", dhf.parse("01/06/2019 14:34:21"), 800.00, "D"));
		movServ.insert(new MovimentacaoDTO(null, conta1.getId(), catRec1.getId(), "Salário", dhf.parse("02/06/2019 08:30:00"), 1000.00, "R"));

		Parcela parc1 = new Parcela(null, 1, df.parse("15/05/2019"), 100.0, 100.0);
		Parcela parc2 = new Parcela(null, 2, df.parse("15/06/2019"), 100.0, 0.0);
		Set<Parcela> parcelas = new HashSet<>();
		parcelas.addAll(Arrays.asList(parc1, parc2));
		PagarReceberDTO pagRecDto = new PagarReceberDTO(null, "Roupas", 1, 200.0, parc1.getVencimento(), catSup1.getId(), cartao1.getId(), parcelas);
		pagRecService.insert(pagRecDto);

		transfServ.insert(new TransferenciaDTO(null, conta1.getId(), cartao1.getId(), dhf.parse("02/06/2019 10:30:55"), 200.0));

		// cartaoServ.fecharFaturas();

		// cartaoServ.pagarFatura(new PagarFaturaDTO(2, 1, 800.0,
		// df.parse("06/06/2019")));
		
		// Teste dos saldos
		conta1 = contaServ.find(conta1.getId());
		cartao1 = cartaoServ.find(cartao1.getId());
		Assert.isTrue(conta1.getSaldo() == 800.00, "Conta com saldo inválido! Esperado: 800 encontrado: " + conta1.getSaldo());
		Assert.isTrue(cartao1.getFaturaAtual() == 700.00, "Cartão com fatura inválida! Esperado: 700.00 encontrado: " + cartao1.getFaturaAtual());
		Assert.isTrue(cartao1.getFaturaFutura() == 100.00, "Cartão com fatura futura inválida! Esperado: 100.00 encontrado: " + cartao1.getFaturaFutura());
		Assert.isTrue(cartao1.getLimiteDisponivel() == 5200.00, "Cartão com limite inválido! Esperado: 5200.00 encontrado: " + cartao1.getLimiteDisponivel());

		// teste usuario logado
		userServ.insert(new Usuario("paulo", "paulo", "paulo@teste.com", "Paulo Ricardo"));
		userServ.usuarioLogadoFake = "paulo";
		Categoria catDesp2 = catServ.insert(new CategoriaDTO(null, "Despesas", "D", null));
		Conta conta2 = contaServ.insert(new Conta(null, "Conta Corrente Paulo", "56789", 0.0));
		Conta conta3 = contaServ.insert(new Conta(null, "Conta Poupança Paulo", "6545", 0.0));
		movServ.insert(new MovimentacaoDTO(null, conta2.getId(), catDesp2.getId(), "Compra no supermercado", dhf.parse("15/06/2019 14:30:00"), 250.00, "D"));
		
		Parcela parc12 = new Parcela(null, 1, df.parse("15/06/2019"), 100.0, 100.0);
		Set<Parcela> parcelas2 = new HashSet<>();
		parcelas.addAll(Arrays.asList(parc12));		
		pagRecService.insert(new PagarReceberDTO(null, "Sofá", 1, 200.0, parc1.getVencimento(), catDesp2.getId(), conta2.getId(), parcelas2));
		transfServ.insert(new TransferenciaDTO(null, conta2.getId(), conta3.getId(), dhf.parse("14/06/2019 00:30:00"), 150.0));
		
		List<Conta> contas = contaServ.findAll();
		Assert.isTrue(contas.size() == 2 &&
		   contas.get(0).getDescricao().equals(conta2.getDescricao()) &&
		   contas.get(1).getDescricao().equals(conta3.getDescricao()),
			"Não filtrou contas do usuário logado corretamente");

	}
}
