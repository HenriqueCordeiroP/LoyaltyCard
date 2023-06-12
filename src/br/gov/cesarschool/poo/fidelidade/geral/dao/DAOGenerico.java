package br.gov.cesarschool.poo.fidelidade.geral.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;

public class DAOGenerico {
	private String diretorioBase;
		
	private static final String EXT = ".dat";
	public DAOGenerico(String diretorioBase) {
		this.diretorioBase = diretorioBase;
		File diretorio = new File(diretorioBase);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
	}
	private File getArquivo(String chave) {
		String nomeArq = chave + EXT;
		return new File(nomeArq);		
	}
	
	private File getArquivo(Identificavel ident) {
		return getArquivo(ident.obterChave());		
	}
	
	private void incluirAux(Identificavel ident, File arq) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(arq);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ident);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir");
		} finally {
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}			
		} 		
	}
	public boolean incluir(Identificavel ident) {
		File arq = getArquivo(ident);
		if (arq.exists()) {
			return false; 
		}
		incluirAux(ident, arq);
		return true; 
	}
	public boolean alterar(Identificavel ident) {
		File arq = getArquivo(ident);
		if (!arq.exists()) {
			return false; 
		}		
		if (!arq.delete()) {
			return false;
		}
		incluirAux(ident, arq);
		return true;
	}
	public Identificavel buscar(String chave) {
		File arq = getArquivo(chave);
		if (!arq.exists()) {
			return null; 
		}				
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(arq);
			ois = new ObjectInputStream(fis);
			return (Identificavel)ois.readObject(); 
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler");
		} finally {
			try {
				ois.close(); 
			} catch (Exception e) {}
			try {
				fis.close(); 
			} catch (Exception e) {}			
		}
	}
	
	public Identificavel[] buscarTodos() {
			File folder = new File(diretorioBase);
			File[] files = folder.listFiles(); 
			Identificavel[] result = new Identificavel[files.length];
			int cont = 0;
			FileInputStream fis = null; 
			ObjectInputStream ois = null;
			for(int i = 0 ; i < files.length ; i ++) {
				File file = files[i];
				if(file.isFile()) { 
					try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					result[cont] = (Identificavel)ois.readObject();
					cont++;
					} catch (Exception e) {
						throw new RuntimeException("Erro ao ler");
					}
				}
			}
			return result;
		}
	}

