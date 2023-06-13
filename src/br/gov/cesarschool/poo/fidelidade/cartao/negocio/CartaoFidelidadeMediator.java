package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import br.gov.cesarschool.fidelidade.geral.excecoes.ExcecaoDadoInvalido;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;

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
	
	public RetornoConsultaExtrato consultaEntreDatas(String numeroCartao, LocalDateTime inicio, LocalDateTime fim) throws ExcecaoDadoInvalido {
		if(StringUtil.ehNuloOuBranco(numeroCartao) == true) {
			//return new RetornoConsultaExtrato(null, "Número do Cartão Inválido");
			throw new ExcecaoDadoInvalido("Número do Cartão Inválido");
		}
		else if(inicio == null) {
			//return new RetornoConsultaExtrato(null, "Data de início inválida");
			throw new ExcecaoDadoInvalido("Data de início inválida");
		}
		else if(!fim.isAfter(inicio)) {
			//return new RetornoConsultaExtrato(null, "Data de fim inválida");
			throw new ExcecaoDadoInvalido("Data de fim inválida");
		}
		LancamentoExtrato[] lancamentos = repositorioLancamento.buscarTodos();
		int i = 0;
		LancamentoExtrato[] temp = new LancamentoExtrato[lancamentos.length];
		for(LancamentoExtrato lancamento : lancamentos) {
			if (lancamento.getNumeroCartao() + "" == numeroCartao
				&& lancamento.getDataHoraLancamento().isAfter(inicio) 
				&& fim == null ? true : lancamento.getDataHoraLancamento().isBefore(fim)) {
				temp[i] = lancamento;
				i++;
			}
		}
		LancamentoExtrato[] result = new LancamentoExtrato[i-1];
		for(int j = 0 ; j < i ; j ++) {
			result[j] = temp[i];
		}
		Ordenador.ordenar(result);
		return new RetornoConsultaExtrato(result, null);
	}
	
}
