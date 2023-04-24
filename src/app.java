import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;

public class app {
	public static void main(String[] args) {
	CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
	Sexo sexo = Sexo.getByCode(1);
	Date dataNascimento = new Date(2004, 05, 28);
	Endereco endereco = new Endereco("Rua Francisco da Cunha", 142, "apto 2301", "51020050", "Recife", "Pernambuco", "Brasil");
	Cliente cliente = new Cliente("09803545442", "Henrique Cordeiro", sexo, dataNascimento, 1000.0, endereco);
	long cartao = mediator.gerarCartao(cliente);
	
	System.out.println(cartao);
	}
}


