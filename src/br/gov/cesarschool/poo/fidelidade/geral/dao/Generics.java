package br.gov.cesarschool.poo.fidelidade.geral.dao;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
public class Generics<T extends Cliente, CartaoFidelidade, LancamentoExtrato> extends Identificavel{
	private T tipo;
	public Generics(T tipo) {
		this.tipo = tipo;
	}
	
}
