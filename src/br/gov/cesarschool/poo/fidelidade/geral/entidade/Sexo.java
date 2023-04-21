package br.gov.cesarschool.poo.fidelidade.geral.entidade;

public enum Sexo {
	MASCULINO(1, "Masculino"),
	FEMININO(2, "Feminino");
	
	private int codigo;
	private String descricao;
	
	private Sexo(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
		
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;	
	}
	
	public static Sexo getByCode(int codigo){
		Sexo[] opcoes = Sexo.values();
		for(Sexo sexo : opcoes) {
			if(sexo.getCodigo() == codigo) {
				return sexo;
			}
		}
		return null;
	}
}
