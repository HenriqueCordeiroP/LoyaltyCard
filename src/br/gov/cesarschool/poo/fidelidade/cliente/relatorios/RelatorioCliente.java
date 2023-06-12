package br.gov.cesarschool.poo.fidelidade.cliente.relatorios;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cliente.negocio.ClienteMediator;

public class RelatorioCliente {
	private ClienteMediator mediator = ClienteMediator.getInstance();
	public void gerarRelatorioClientes() {
		Cliente[] clientes = mediator.consultarClientesOrdenadosPorNome();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		decimalFormat.setMinimumFractionDigits(2);
		for(Cliente cliente : clientes) {
			String formattedDate = dateFormat.format(cliente.getDataDeNascimento());
			String formattedRenda = decimalFormat.format(cliente.getRenda());
			System.out.println(cliente.getNomeCompleto() + " - " + formattedDate + " - " + formattedRenda);
		}
	}

}
