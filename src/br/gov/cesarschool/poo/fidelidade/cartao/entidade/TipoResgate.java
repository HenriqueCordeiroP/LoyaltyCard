package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

public enum TipoResgate {
	PRODUTO(0, "Produto"),
	SERVICO(1, "Servico"),
	VIAGEM(2, "Viagem");
	
	private int codigo;
	private String descricao;
//	private static final TipoResgate opcoes[] = {PRODUTO, SERVICO, VIAGEM};
	
	private TipoResgate(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoResgate obterPorCodigo(int codigo) {
		TipoResgate[] opcoes = TipoResgate.values();
		for(TipoResgate tipoResgate : opcoes) {
			if(codigo == tipoResgate.getCodigo()) {
				return tipoResgate;
			}
		}
		return null;
	}
}
