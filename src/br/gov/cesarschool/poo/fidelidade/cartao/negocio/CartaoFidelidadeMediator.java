package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CartaoFidelidadeMediator {
	private CartaoFidelidadeDAO repositorioCartao;
	private LancamentoExtratoDAO repositorioLancamento;
	private static CartaoFidelidadeMediator instance;
	
	private CartaoFidelidadeMediator() {
		repositorioCartao = new CartaoFidelidadeDAO();
		repositorioLancamento = new LancamentoExtratoDAO();
		}
	
	public static CartaoFidelidadeMediator getInstance() {
		if(instance == null) {
			instance = new CartaoFidelidadeMediator();
		}
		return instance;
	}
	
	public long gerarCartao(Cliente cliente) {
		String oldCpf = cliente.getCpf();
		String cpf = oldCpf.substring(0, oldCpf.length() - 2); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(cliente.getDataDeNascimento());
		String numCartaoStr = cpf +  cal.get(Calendar.YEAR) + 
				getMonth(cal) + getDay(cal);
		long numCartao = Long.parseLong(numCartaoStr);
		CartaoFidelidade cartao = new CartaoFidelidade(numCartao); 
		boolean resultIncluirCartao = repositorioCartao.incluir(cartao);
		if(resultIncluirCartao == true) {
			return numCartao;
		}
		return 0L;
	}
	
	private String getMonth(Calendar cal) {
		int month = cal.get(Calendar.MONTH);
		if(month < 10) {
			return "0" + (month);
		}
		return "" + (month);
	}
	
	private String getDay(Calendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(day < 10) {
			return "0" + day;
		}
		return "" + day;
	}
	
	public String pontuar(long numeroCartao, double quantidadePontos) {
		if(quantidadePontos <= 0) {
			return "Quantidade inválida de pontos.";
		}
		CartaoFidelidade cartao = repositorioCartao.buscar("" + numeroCartao);
		if (cartao == null) {
			return "Cartão não encontrado.";
		}
		cartao.creditar(quantidadePontos);
		repositorioCartao.alterar(cartao);
		Date dataHoraLancamento = new Date();
		LocalDateTime dataHoraConvertido = dataHoraLancamento.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		int qtdPontosInt = (int) quantidadePontos; // pode perder informação
		LancamentoExtratoPontuacao lancamento = new LancamentoExtratoPontuacao(cartao.getNumero(), qtdPontosInt, dataHoraConvertido );
		repositorioLancamento.incluir(lancamento);
		return null;
	}
	
	public String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo) {
		if(quantidadePontos <= 0) {
			return "Quantidade inválida de pontos.";
		}
		CartaoFidelidade cartao = repositorioCartao.buscar("" + numeroCartao);
		if(cartao == null) {
			return "Cartão não encontrado.";
		}
		if(cartao.getSaldo() < quantidadePontos) {
			return "Saldo Insuficiente.";
		}
		cartao.debitar(quantidadePontos);
		repositorioCartao.alterar(cartao);
		Date dataHoraLancamento = new Date();
		LocalDateTime dataHoraConvertido = dataHoraLancamento.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		int qtdPontosInt = (int) quantidadePontos; // pode perder informação
		LancamentoExtratoResgate lancamento = new LancamentoExtratoResgate(numeroCartao, qtdPontosInt, dataHoraConvertido, tipo);
		repositorioLancamento.incluir(lancamento);
		return null;
	}
	
	public CartaoFidelidade buscar(long numero) {
		return repositorioCartao.buscar("" + numero);
	}
	
}
