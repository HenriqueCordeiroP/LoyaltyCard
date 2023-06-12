import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;

public class app {
	public static void main(String[] args) {
	CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
	Sexo sexo = Sexo.getByCode(1);
	Date dataNascimento = new Date(2004, 4, 28);
	Endereco endereco = new Endereco("Rua Francisco da Cunha", 142, "apto 2301", "51020050", "Recife", "Pernambuco", "Brasil");
	Cliente cliente = new Cliente("09803545442", "Henrique Cordeiro", sexo, dataNascimento, 1000000.10, endereco);
	long cartao = mediator.gerarCartao(cliente);
	LancamentoExtrato lancamento = new LancamentoExtratoResgate(cartao, 100, LocalDateTime.now(), TipoResgate.PRODUTO );
	DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
	decimalFormat.setMinimumFractionDigits(2);
	String formatted = decimalFormat.format(cliente.getRenda());
//	System.out.println(formatted);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	System.out.println(lancamento.getDataHoraLancamento().getDayOfMonth() + "/" + lancamento.getDataHoraLancamento().getMonthValue() + "/" + lancamento.getDataHoraLancamento().getYear());
	System.out.println(lancamento.getDataHoraLancamento().getHour() + ":" + fixDate(lancamento.getDataHoraLancamento().getMinute()) + ":" + fixDate(lancamento.getDataHoraLancamento().getSecond()));
	//	String formatted = dateFormat.format(cliente.getDataDeNascimento());
//	System.out.println(formatted);
//	System.out.println(cliente.obterIdade());
//	System.out.println(cartao);
	}
	public static String fixDate(int num) {
		return num >= 10 ? "" + num : "0"+num;
	}
}


