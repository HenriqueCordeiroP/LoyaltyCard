package br.gov.cesarschool.poo.fidelidade.cliente.negocio;

import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;

public class ClienteMediator {
	private ClienteDAO repositorioCliente;
	private CartaoFidelidadeMediator cartaoMediator;
	private static ClienteMediator instance;
	
	private ClienteMediator() {
		repositorioCliente = new ClienteDAO();
		cartaoMediator = CartaoFidelidadeMediator.getInstance();
		}
	
	public static ClienteMediator getInstance() {
		if(instance == null) {
			instance = new ClienteMediator();
		}
		return instance;
	}
	
	public ResultadoInclusaoCliente incluir(Cliente cliente) {
		String resultValidar = validar(cliente);
		long numeroCartao = 0;
		if(resultValidar == null) {
			repositorioCliente.incluir(cliente);
			numeroCartao = cartaoMediator.gerarCartao(cliente);
		}
		
		return new ResultadoInclusaoCliente(numeroCartao, resultValidar);
	}
	
	public String alterar(Cliente cliente) {
		String resultValidar = validar(cliente);
		if(resultValidar != null) {
			return resultValidar;
		}
		if(repositorioCliente.buscar(cliente.getCpf()) == null){
			return "CLIENTE NAO ENCONTRADO";
		} 
		
		repositorioCliente.alterar(cliente); 
		return null;
	}
	 
	private String validar(Cliente cliente) {
		Date today = new Date();
		if(!ValidadorCPF.ehCpfValido(cliente.getCpf())) {
			return "CPF INVALIDO";
		}
		if(!StringUtil.ehNuloOuBranco(cliente.getNomeCompleto())) {
			return "NOME INVALIDO";
		}
		if(!cliente.getDataDeNascimento().before(today)) {
			return "DATA DE NASCIMENTO INVALIDA";
		}
		if(cliente.obterIdade() < 18) {
			return "IDADE INSUFICIENTE";
		}
		if(cliente.getRenda() < 0) {
			return "RENDA INSUFICIENTE";
		}
		String resultEndereco = validarEndereco(cliente.getEndereco());
		if(resultEndereco != null) {
			return resultEndereco;
		}
			
		return null;
	}
	
	private String validarEndereco(Endereco endereco) {
		if(endereco.getLogradouro().length() < 4 || StringUtil.ehNuloOuBranco(endereco.getLogradouro())) {
			return "LOGRADOURO INVALIDO";
		}
		if(endereco.getNumero() >= 0 ) {
			return "NUMERO INVALIDO";
		}
		if(StringUtil.ehNuloOuBranco(endereco.getCidade())) {
			return "CIDADE INVALIDA";
		}
		if(StringUtil.ehNuloOuBranco(endereco.getEstado())) {
			return "ESTADO INVALIDO";
		}
		if(StringUtil.ehNuloOuBranco(endereco.getPais())){
			return "PAIS INVALIDO";
		}
		return null;
	
	}
}
