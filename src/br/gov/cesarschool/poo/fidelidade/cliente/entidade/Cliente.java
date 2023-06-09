package br.gov.cesarschool.poo.fidelidade.cliente.entidade;

import java.util.Calendar;
import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Comparavel;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;
import lombok.ToString;

@ToString(exclude = "dataDeNascimento")
public class Cliente extends Identificavel implements Comparavel { 
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
		this.dataDeNascimento = fixDate(dataDeNascimento);
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
	
	public String obterChave() {
		return cpf;
	}
	
	public int comparar(Comparavel comp) {
		Cliente comparado = (Cliente) comp;
		return nomeCompleto.compareTo(comparado.getNomeCompleto());
	}
	
	private Date fixDate(Date data) {
		int newYear = data.getYear() - 1900;
		Date novaData = new Date(newYear, data.getMonth(), data.getDate());
		return novaData;
	}
	
}
