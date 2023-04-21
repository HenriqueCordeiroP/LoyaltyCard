package br.gov.cesarschool.poo.fidelidade.cliente.entidade;

import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;

import java.io.Serializable;
import java.util.Calendar;

public class Cliente implements Serializable { 
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nomeCompleto;		
	private Sexo sexo;
	private Date dataDeNascimento;
	private double renda;
	private Endereco endereco;
	
	public Cliente(String cpf, String nomeCompleto, Sexo sexo, Date dataDeNascimento, double renda, Endereco endereco) {
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.renda = renda;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public int obterIdade() {
		Calendar calBirthDate = Calendar.getInstance();
		calBirthDate.setTime(this.dataDeNascimento);
		int anoNascimento = calBirthDate.get(Calendar.YEAR);
		Calendar calCurrent = Calendar.getInstance();
		int anoAtual = calCurrent.get(Calendar.YEAR);
		
		return anoAtual - anoNascimento;
	}
	
}
