package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Comparavel;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;

public abstract class LancamentoExtrato extends Identificavel implements Serializable, Comparavel{
	private long numeroCartao;
	private int quantidadePontos;
	private LocalDateTime dataHoraLancamento;
	
	public LancamentoExtrato(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
		this.numeroCartao = numeroCartao;
		this.quantidadePontos = quantidadePontos;
		this.dataHoraLancamento = dataHoraLancamento;
	}

	public long getNumeroCartao() {
		return numeroCartao;
	}

	public int getQuantidadePontos() {
		return quantidadePontos;
	}

	public LocalDateTime getDataHoraLancamento() {
		return dataHoraLancamento;
	}
	
	public String obterChave() {
		return "" + numeroCartao;
	}
	
	public int comparar(Comparavel comp) {
		LancamentoExtrato comparado = (LancamentoExtrato) comp;
		return dataHoraLancamento.compareTo(comparado.getDataHoraLancamento());
	}
	
	public abstract String getIdentificadorTipo();
}
