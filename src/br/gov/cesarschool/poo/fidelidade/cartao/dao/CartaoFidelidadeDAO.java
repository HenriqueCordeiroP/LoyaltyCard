package br.gov.cesarschool.poo.fidelidade.cartao.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;

/* Apesar de não acusar erros no código, não sei se a lógica está correta
 * vale uma revisão com o prof
 */
public class CartaoFidelidadeDAO {
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "banco" + FILE_SEP 
			+ "fidelidade" + FILE_SEP; 
	private static final String EXT = ".dat";
	public CartaoFidelidadeDAO() {
		File diretorio = new File(DIR_BASE);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
	}
	private File getArquivo(String cpf) {
		String nomeArq = DIR_BASE + cpf + EXT;
		return new File(nomeArq);		
	}
	private void incluirAux(CartaoFidelidade cartaofidelidade) {
		File arq = getArquivo("" + cartaofidelidade.getNumero());
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(arq);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cartaofidelidade);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir cartao fidelidade");
		} finally {
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}			
		} 		
	}
	public boolean incluir(CartaoFidelidade cartaofidelidade) {
		File arq = getArquivo(cartaofidelidade.getNumero() + "");
		if (arq.exists()) {
			return false; 
		}
		incluirAux(cartaofidelidade);
		return true; 
	}
	public boolean alterar(CartaoFidelidade cartaofidelidade) {
		File arq = getArquivo(cartaofidelidade.getNumero() + "");
		if (!arq.exists()) {
			return false; 
		}		
		if (!arq.delete()) {
			return false;
		}
		incluirAux(cartaofidelidade);
		return true;
	}
	public CartaoFidelidade buscar(long numero) {
		File arq = getArquivo(numero + "");
		if (!arq.exists()) {
			return null; 
		}				
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(arq);
			ois = new ObjectInputStream(fis);
			return (CartaoFidelidade)ois.readObject(); 
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler cliente");
		} finally {
			try {
				ois.close(); 
			} catch (Exception e) {}
			try {
				fis.close(); 
			} catch (Exception e) {}			
		}
	}
}