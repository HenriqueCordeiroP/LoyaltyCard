package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import java.time.LocalDateTime;
import java.util.Calendar;

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
		String cpf = cliente.getCpf();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cliente.getDataDeNascimento());
		String cartao = cpf +  cal.get(Calendar.YEAR) + 
				getMonth(cal) + getDay(cal);
		return Long.parseLong(cartao);
	}
	
	private String getMonth(Calendar cal) {
		int month = cal.get(Calendar.MONTH);
		if(month < 10) {
			return "0" + (month + 1);
		}
		return "" + (month + 1);
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
			return "Error message";
		}
		return null;
	}
	
	public String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo) {
		return null;
	}
	
	
}
