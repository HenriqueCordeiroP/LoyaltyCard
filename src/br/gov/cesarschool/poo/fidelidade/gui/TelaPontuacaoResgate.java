package br.gov.cesarschool.poo.fidelidade.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class TelaPontuacaoResgate {

	protected Shell shell;
	private Text txtNumeroCartao;
	private Label lblNewLabel;
	private Label lblOperao;
	private Button btnBuscar;
	private Combo combo;
	private Button btnPontuacao;
	private Button btnResgate;
	private Label lblSaldo;
	private Combo cmbTipoResgate;
	private Text txtSaldo;
	private Text txtValor;
	private Label lblTipoResgate;
	private Label lblValor;
	private Button btnPontuarResgatar;
	private Button btnVoltar;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaPontuacaoResgate window = new TelaPontuacaoResgate();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setToolTipText("");
		shell.setSize(580, 380);
		shell.setText("LoyaltyCard");
		
		txtNumeroCartao = new Text(shell, SWT.BORDER);
		txtNumeroCartao.setBounds(193, 39, 188, 25);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		this.lblNewLabel = lblNewLabel;
		lblNewLabel.setBounds(85, 42, 104, 15);
		lblNewLabel.setText("Número do Cartão");
		
		Label lblOperao = new Label(shell, SWT.NONE);
		this.lblOperao = lblOperao;
		lblOperao.setBounds(85, 84, 55, 15);
		lblOperao.setText("Operação");
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
		btnBuscar.setBounds(406, 37, 75, 25);
		btnBuscar.setText("Buscar");
		
		Button btnPontuacao = new Button(shell, SWT.RADIO);
		this.btnPontuacao = btnPontuacao;
		btnPontuacao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnPontuacao.setBounds(193, 83, 90, 16);
		btnPontuacao.setText("Pontuação");
		
		Button btnResgate = new Button(shell, SWT.RADIO);
		this.btnResgate = btnResgate;
		btnResgate.setBounds(193, 112, 90, 16);
		btnResgate.setText("Resgate");
		
		txtSaldo = new Text(shell, SWT.BORDER);
		txtSaldo.setEnabled(false);
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(193, 152, 76, 21);
		
		Label lblSaldo = new Label(shell, SWT.NONE);
		this.lblSaldo = lblSaldo;
		lblSaldo.setBounds(85, 155, 75, 15);
		lblSaldo.setText("Saldo Atual");
		
		Combo cmbTipoResgate = new Combo(shell, SWT.NONE);
		cmbTipoResgate.setEnabled(false);
		this.cmbTipoResgate = cmbTipoResgate;
		cmbTipoResgate.setItems(new String[] {"Produto", "Serviço", "Viagem"});
		cmbTipoResgate.setBounds(193, 199, 91, 23);
		
		Label lblTipoResgate = new Label(shell, SWT.NONE);
		this.lblTipoResgate = lblTipoResgate;
		lblTipoResgate.setText("Tipo de Resgate");
		lblTipoResgate.setBounds(85, 203, 102, 15);
		
		Label lblValor = new Label(shell, SWT.NONE);
		this.lblValor = lblValor;
		lblValor.setBounds(85, 253, 55, 15);
		lblValor.setText("Valor");
		
		txtValor = new Text(shell, SWT.BORDER);
		txtValor.setEnabled(false);
		txtValor.setBounds(193, 248, 76, 21);
		
		Button btnPontuarResgatar = new Button(shell, SWT.NONE);
		btnPontuarResgatar.setEnabled(false);
		this.btnPontuarResgatar = btnPontuarResgatar;
		btnPontuarResgatar.setBounds(193, 295, 115, 25);
		btnPontuarResgatar.setText("Pontuar / Resgatar");
		
		Button btnVoltar = new Button(shell, SWT.NONE);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtNumeroCartao.setText("");
				btnPontuacao.setSelection(false);
				btnResgate.setSelection(false);
				txtSaldo.setText("");
				txtSaldo.setEnabled(false);
				cmbTipoResgate.deselectAll();
				cmbTipoResgate.setEnabled(false);
				txtValor.setText("");
				txtValor.setEnabled(false);
				btnPontuarResgatar.setEnabled(false);
				
			}
		});
		this.btnVoltar = btnVoltar;
		btnVoltar.setBounds(406, 295, 75, 25);
		btnVoltar.setText("Voltar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDown(MouseEvent e) {
				CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
				String numero = txtNumeroCartao.getText();
				char op;
				
				if(StringUtil.ehNuloOuBranco(numero)) {
					JOptionPane.showMessageDialog(null, "Insira o número do cartão.");
					return;
				}
				
				if(btnResgate.getSelection() == false
						&& btnPontuacao.getSelection() == false) {
					JOptionPane.showMessageDialog(null, "Selecione uma operação.");
					return;
				} else {
					op = btnResgate.getSelection() == true ? 'R' : 'P';
				}
				
				long numeroLong = Long.parseLong(numero);
				btnPontuarResgatar.setEnabled(true);
				txtSaldo.setEnabled(true);
				txtSaldo.setText("???");
				txtValor.setEnabled(true);
				cmbTipoResgate.setEnabled(true);
				
				if(op == 'R') {
					double valor;
					TipoResgate tipoResgate;
					int indexResgate = cmbTipoResgate.getSelectionIndex();
					
					if(indexResgate == -1) {
						JOptionPane.showMessageDialog(null, "Selecione um tipo de resgate.");
						return;
					} else {						
					tipoResgate = TipoResgate.obterPorCodigo(indexResgate); 
					}
					
					String valorStr = txtValor.getText();
					if(StringUtil.ehNuloOuBranco(valorStr)) {
						JOptionPane.showMessageDialog(null, "Valor Inválido");
						return;
					}else { 
						valor = Double.parseDouble(txtValor.getText());
					}
					
					String resultResgatar = mediator.resgatar(numeroLong, valor, tipoResgate);
					if(resultResgatar != null) {
						JOptionPane.showMessageDialog(null, resultResgatar);
						return;
					}
				}
			}
		});
	}
}
