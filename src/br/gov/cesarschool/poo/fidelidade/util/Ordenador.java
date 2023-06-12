package br.gov.cesarschool.poo.fidelidade.util;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Comparavel;

public class Ordenador {
	public static void ordenar(Comparavel[] comp) {
		for(int i = 0 ; i < comp.length ; i++) {
			for(int j = i+1 ; j < comp.length ; j++) {
				if(comp[i].comparar(comp[j]) < 0) {
					Comparavel temp = comp[i];
					comp[i] = comp[j];
					comp[j] = temp;
				}
			}
		}
	}
}
