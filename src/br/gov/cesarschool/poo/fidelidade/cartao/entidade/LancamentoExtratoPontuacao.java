package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LancamentoExtratoPontuacao extends LancamentoExtrato implements Serializable{

	public LancamentoExtratoPontuacao(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
		super(numeroCartao, quantidadePontos, dataHoraLancamento);
	}
	
	public String getIdentificadorTipo() {
		return "P";
	}
}	
