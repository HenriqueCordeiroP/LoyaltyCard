package br.gov.cesarschool.poo.fidelidade.cartao.relatorios;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.excecoes.ExcecaoDadoInvalido;

public class RelatorioExtrato {
	private static CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
	
	private static final Scanner ENTRADA = new Scanner(System.in);
	
	public static void gerarRelatorioExtratos(long numeroCartao, LocalDateTime inicio, LocalDateTime fim) throws ExcecaoDadoInvalido {
		RetornoConsultaExtrato consulta = mediator.consultaEntreDatas(Long.toString(numeroCartao), inicio, fim);
		LancamentoExtrato[] extratos = consulta.getLancamentos();
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		decimalFormat.setMinimumFractionDigits(2);
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
	
	public static String fixNum(int num) {
		return num >= 10 ? "" + num : "0"+num;
	}
	
	public static String getFormattedDateFromLocalDateTime(LocalDateTime dataHora) {
		return fixNum(dataHora.getDayOfMonth()) + "/" + fixNum(dataHora.getMonthValue()) + "/" + fixNum(dataHora.getYear());
	}
	
	public static String getFormattedTimeFromLocalDateTime(LocalDateTime dataHora) {
		return fixNum(dataHora.getHour()) + ":" + fixNum(dataHora.getMinute()) + ":" + fixNum(dataHora.getSecond());
	}
	
	public static void main(String[] args) {
		long numeroCartao = ENTRADA.nextLong();
		
		String dataInicio = ENTRADA.nextLine();
		LocalDateTime inicio = LocalDateTime.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		
		String dataFim = ENTRADA.nextLine();
		LocalDateTime fim = LocalDateTime.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		
		try {
			gerarRelatorioExtratos(numeroCartao, inicio, fim);
		} catch (ExcecaoDadoInvalido e) {
			System.out.println(e.getMessage());
		}
	}
}
