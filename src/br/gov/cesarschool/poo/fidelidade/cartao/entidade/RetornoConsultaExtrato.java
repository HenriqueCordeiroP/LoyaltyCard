package br.gov.cesarschool.poo.fidelidade.cartao.entidade;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class RetornoConsultaExtrato {
	private LancamentoExtrato[] lancamentos;
	private String mensagemErro;
}
