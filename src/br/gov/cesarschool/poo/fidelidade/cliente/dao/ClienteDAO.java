package br.gov.cesarschool.poo.fidelidade.cliente.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Comparator;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.dao.DAOGenerico;

public class ClienteDAO {
	private DAOGenerico daoEncapsulado;
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "fidelidade" + FILE_SEP 
			+ "cliente" + FILE_SEP; 
	public ClienteDAO() {
		daoEncapsulado = new DAOGenerico (DIR_BASE);
	}
	public boolean incluir(Cliente cliente) {
		return daoEncapsulado.incluir(cliente);
	}
	public boolean alterar(Cliente cliente) {
		return daoEncapsulado.alterar(cliente);
	}
	public Cliente buscar(String cpf) {
		return (Cliente) daoEncapsulado.buscar(cpf);
	}
	
	public Cliente[] buscarTodos() {
		return (Cliente[]) daoEncapsulado.buscarTodos();
	}
}
