package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
import lombok.ToString;

@ToString(exclude = "dataHoraAtualizacao")
public class CartaoFidelidade extends Identificavel{
	private long numero;
	private double saldo;
	private Date dataHoraAtualizacao = new Date();
	
	public CartaoFidelidade(long numero) {
		this.numero = numero;
	}
	
	public long getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public Date getDataHora() {
		return dataHoraAtualizacao;
	}
	
	public void creditar(double valor) {
		saldo = saldo + valor;
		dataHoraAtualizacao = new Date();
	}
	
	public void debitar(double valor) {
		saldo = saldo - valor;
		dataHoraAtualizacao = new Date();
	}
	
	public String obterChave() {
		return "" + numero;
	}
}

