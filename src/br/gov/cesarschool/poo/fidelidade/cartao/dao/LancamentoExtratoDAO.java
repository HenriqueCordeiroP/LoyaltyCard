package br.gov.cesarschool.poo.fidelidade.cartao.dao;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.geral.dao.DAOGenerico;

public class LancamentoExtratoDAO {
	private DAOGenerico daoEncapsulado;
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "fidelidade" + FILE_SEP 
			+ "lancamento" + FILE_SEP;
	
	public LancamentoExtratoDAO() {
		daoEncapsulado = new DAOGenerico(DIR_BASE);
	}
	
	public boolean incluir(LancamentoExtratoPontuacao credito) {
		return daoEncapsulado.incluir(credito);
	}
	public boolean incluir(LancamentoExtratoResgate debito) {
		return daoEncapsulado.incluir(debito);
	}	
	
	public LancamentoExtrato[] buscarTodos() {
		return (LancamentoExtrato[]) daoEncapsulado.buscarTodos();
	}
}