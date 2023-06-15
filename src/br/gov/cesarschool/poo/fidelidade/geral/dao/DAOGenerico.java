package br.gov.cesarschool.poo.fidelidade.geral.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;

public class DAOGenerico<T extends Identificavel> {
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
        String nomeArq = diretorioBase + chave + EXT;
        return new File(nomeArq);        
    }
    
    private File getArquivo(T ident) {
        return getArquivo(ident.obterChave());        
    }
    
    private void incluirAux(T ident, File arq) {
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
    public boolean incluir(T ident) {
        File arq = getArquivo(ident);
        if (arq.exists()) {
            return false; 
        }
        incluirAux(ident, arq);
        return true; 
    }
    public boolean alterar(T ident) {
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
    public T buscar(String chave) {
        File arq = getArquivo(chave);
        if (!arq.exists()) {
            return null; 
        }               
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(arq);
            ois = new ObjectInputStream(fis);
            return (T) ois.readObject(); 
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
    
    public T[] buscarTodos() {
        File folder = new File(diretorioBase);
        if(!folder.exists()) {
            return null;
        }
        FileInputStream fis = null; 
        ObjectInputStream ois = null;
        try {
            File[] files = folder.listFiles(); 
            if(files.length == 0) {
                return (T[]) new Object[0];
            }
            T[] result = (T[]) new Object[files.length];
            int cont = 0;
            for(int i = 0 ; i < files.length ; i ++) {
                File file = files[i];
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                result[cont] = (T) ois.readObject();
                cont++;
                
            }
            T[] res = (T[]) new Object[cont - 1];
            for(int j = 0 ; j < cont-1 ; j ++) {
                res[j] = result[j];
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler");
        } finally {
            try {
            	if(ois != null) {
            		ois.close();
            	}
            } catch(Exception e) {
                e.printStackTrace();
            }try {
            	if(fis != null){
            		fis.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}