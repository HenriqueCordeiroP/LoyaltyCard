package br.gov.cesarschool.poo.fidelidade.util;

import java.io.Serializable;

public class StringUtil implements Serializable{
	public static boolean ehNuloOuBranco(String str) {
		return (str == null || str.trim().isEmpty());
	}
}
