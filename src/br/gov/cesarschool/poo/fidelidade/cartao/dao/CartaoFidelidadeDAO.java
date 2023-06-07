package br.gov.cesarschool.poo.fidelidade.cartao.dao;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.geral.dao.DAOGenerico;

public class CartaoFidelidadeDAO {
	private DAOGenerico daoEncapsulado;
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "fidelidade" + FILE_SEP 
			+ "cartao" + FILE_SEP; 
	
	public CartaoFidelidadeDAO() {
		daoEncapsulado = new DAOGenerico(DIR_BASE);
	}
	
	public boolean incluir(CartaoFidelidade cartaofidelidade) {
		return daoEncapsulado.incluir(cartaofidelidade); 
	}
	public boolean alterar(CartaoFidelidade cartaofidelidade) {
		return daoEncapsulado.alterar(cartaofidelidade);
	}
	public CartaoFidelidade buscar(String numero) {
		return (CartaoFidelidade)daoEncapsulado.buscar(numero);
	}
}