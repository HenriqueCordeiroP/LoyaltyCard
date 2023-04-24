package br.gov.cesarschool.poo.fidelidade.cartao.dao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LancamentoExtratoDAO {
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "fidelidade" + FILE_SEP 
			+ "lancamento" + FILE_SEP;
	
	private static final String EXT = ".dat";
	
	public LancamentoExtratoDAO() {
		File diretorio = new File(DIR_BASE);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
	}
	private File getArquivo(String tipoLancamento, long numeroCartao) { 
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("-", "").replace(":", "").replace(".", "").replace("T", "");
		String idLancamento = tipoLancamento + numeroCartao + timestamp; 
		String nomeArq = DIR_BASE + idLancamento + EXT;
		return new File(nomeArq);		
	}
	
	private void incluirAux(LancamentoExtrato lancamento, String tipo) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File arq = getArquivo(tipo, lancamento.getNumeroCartao());
		try {
			fos = new FileOutputStream(arq);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(lancamento);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir conta");
		} finally {
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}			
		} 		
	}	
	public boolean incluir(LancamentoExtratoPontuacao credito) {
		incluirAux(credito, "P");
		return true;
	}
	public boolean incluir(LancamentoExtratoResgate debito) {
		incluirAux(debito, "R");
		return true;
	}	
}