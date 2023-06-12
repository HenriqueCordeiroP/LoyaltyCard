package br.gov.cesarschool.poo.fidelidade.cartao.relatorios;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;

public class RelatorioExtrato {
	private CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
	private static final Scanner IN = new Scanner(System.in);
	public void gerarRelatorioExtratos(String numeroCartao, LocalDateTime inicio, LocalDateTime fim) {
		RetornoConsultaExtrato consulta = mediator.consultaEntreDatas(numeroCartao, inicio, fim);
		LancamentoExtrato[] extratos = consulta.getLancamentos();
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		for(LancamentoExtrato extrato : extratos) {
			try {
				LocalDateTime dataHora = extrato.getDataHoraLancamento();
				String data = getFormattedDateFromLocalDateTime(dataHora);
				String time = getFormattedTimeFromLocalDateTime(dataHora);
				String formattedValor = decimalFormat.format(extrato.getQuantidadePontos());
				System.out.println(data +  time + " - " + formattedValor + " - " + extrato.getIdentificadorTipo());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public String fixNum(int num) {
		return num >= 10 ? "" + num : "0"+num;
	}
	
	public String getFormattedDateFromLocalDateTime(LocalDateTime dataHora) {
		return fixNum(dataHora.getDayOfMonth()) + "/" + fixNum(dataHora.getMonthValue()) + "/" + fixNum(dataHora.getYear());
	}
	
	public String getFormattedTimeFromLocalDateTime(LocalDateTime dataHora) {
		return fixNum(dataHora.getHour()) + ":" + fixNum(dataHora.getMinute()) + ":" + fixNum(dataHora.getSecond());
	}
	
//	public static void main(String[] args) {
//		String numeroCartao = IN.next();
//		LocalDateTime inicio = IN.next ?
//	}
}
