package br.gov.cesarschool.poo.fidelidade.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cliente.negocio.ClienteMediator;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

public class TelaManutencaoCliente {

	protected Shell shell;
	private Text text_cpf;
	private Text text_nome_completo;
	private Text dataNascimento;
	private Text text_renda;
	private Text text_logradouro;
	private Text text_numero;
	private Text text_complemento;
	private Text text_cep;
	private Text text_cidade;
	private Combo combo_estado;
	private Button btnBuscar;
	private Button btnIncluir;
	private Group grupoSexo;
	private Button btnFeminino;
	private Button btnMasculino;
	private Button btnLimpar;
	private Button btnVoltar;
	private Label labelErro;
	private Label labelErroCpf;
	
	ClienteMediator mediator = ClienteMediator.getInstance();
	
	private Sexo sexo;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaManutencaoCliente window = new TelaManutencaoCliente();
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setToolTipText("");
		shell.setSize(580, 473);
		shell.setText("LoyaltyCard");
		
		Label lblCpf = new Label(shell, SWT.NONE);
		lblCpf.setBounds(115, 15, 55, 18);
		lblCpf.setText("CPF");
		
		text_cpf = new Text(shell, SWT.BORDER);
		text_cpf.setBounds(233, 12, 76, 21);
		
		Label lblNomeCompleto = new Label(shell, SWT.NONE);
		lblNomeCompleto.setBounds(115, 49, 112, 18);
		lblNomeCompleto.setText("Nome Completo");
		
		text_nome_completo = new Text(shell, SWT.BORDER);
		text_nome_completo.setEnabled(false);
		text_nome_completo.setBounds(233, 46, 76, 21);
		
		Label data = new Label(shell, SWT.NONE);
		data.setBounds(115, 119, 112, 18);
		data.setText("Data de nascimento");
		
		dataNascimento = new Text(shell, SWT.BORDER);
		dataNascimento.setEnabled(false);
		dataNascimento.setToolTipText("dd/mm/aaaa");
		dataNascimento.setBounds(233, 116, 76, 21);
		
		Label lblRenda = new Label(shell, SWT.NONE);
		lblRenda.setBounds(115, 155, 55, 18);
		lblRenda.setText("Renda");
		
		text_renda = new Text(shell, SWT.BORDER);
		text_renda.setEnabled(false);
		text_renda.setBounds(233, 152, 76, 21);
		
		Label lblLogradouro = new Label(shell, SWT.NONE);
		lblLogradouro.setBounds(115, 190, 89, 18);
		lblLogradouro.setText("Logradouro");
		
		text_logradouro = new Text(shell, SWT.BORDER);
		text_logradouro.setEnabled(false);
		text_logradouro.setBounds(233, 187, 76, 21);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(115, 227, 55, 25);
		lblNewLabel_2.setText("Número");
		
		text_numero = new Text(shell, SWT.BORDER);
		text_numero.setEnabled(false);
		text_numero.setBounds(233, 221, 76, 21);
		
		Label lblComplemento = new Label(shell, SWT.NONE);
		lblComplemento.setBounds(115, 258, 89, 18);
		lblComplemento.setText("Complemento");
		
		text_complemento = new Text(shell, SWT.BORDER);
		text_complemento.setEnabled(false);
		text_complemento.setBounds(233, 255, 76, 21);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(115, 288, 55, 18);
		lblNewLabel_3.setText("CEP");
		
		text_cep = new Text(shell, SWT.BORDER);
		text_cep.setEnabled(false);
		text_cep.setBounds(233, 285, 76, 21);
		
		Label lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBounds(115, 322, 55, 18);
		lblCidade.setText("Cidade");
		
		text_cidade = new Text(shell, SWT.BORDER);
		text_cidade.setEnabled(false);
		text_cidade.setBounds(233, 319, 76, 21);
		
		Label lblEstado = new Label(shell, SWT.NONE);
		lblEstado.setBounds(115, 356, 55, 25);
		lblEstado.setText("Estado");
		
		Combo combo_estado = new Combo(shell, SWT.NONE);
		combo_estado.setEnabled(false);
		this.combo_estado = combo_estado;
		combo_estado.setBounds(232, 353, 91, 23);
		String[] itens = {"Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"};
		combo_estado.setItems(itens);
		
		Button btnNovo = new Button(shell, SWT.NONE);
		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(!ValidadorCPF.ehCpfValido(text_cpf.getText().trim())) {
					labelErroCpf.setVisible(true);
					labelErroCpf.setText("CPF em formato inválido");
				}
				else {
					labelErroCpf.setVisible(false);
					if(mediator.buscarCPF(text_cpf.getText().trim()) == null){
						text_cpf.setEnabled(false);
						btnNovo.setEnabled(false);
						btnBuscar.setEnabled(false);
						btnIncluir.setText("Incluir");
						
						text_nome_completo.setEnabled(true);
						dataNascimento.setEnabled(true);
						grupoSexo.setEnabled(true);
						text_renda.setEnabled(true);
						text_logradouro.setEnabled(true);
						text_numero.setEnabled(true);
						text_complemento.setEnabled(true);
						text_cep.setEnabled(true);
						text_cidade.setEnabled(true);
						combo_estado.setEnabled(true);
						btnIncluir.setEnabled(true);
						btnLimpar.setEnabled(true);
						btnVoltar.setEnabled(true);
					}
					else {
						labelErroCpf.setVisible(true);
						labelErroCpf.setText("CPF já foi cadastrado!");
					}
				}	
			}
		});
		btnNovo.setBounds(329, 10, 75, 25);
		btnNovo.setText("Novo");
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!ValidadorCPF.ehCpfValido(text_cpf.getText().trim())) {
					labelErroCpf.setVisible(true);
					labelErroCpf.setText("CPF em formato inválido");
				}
				else {
					if(mediator.buscarCPF(text_cpf.getText().trim()) == null){
						labelErroCpf.setVisible(true);
						labelErroCpf.setText("CPF ainda não foi cadastrado!");
					}
					else {
						text_cpf.setEnabled(false);
						btnNovo.setEnabled(false);
						btnBuscar.setEnabled(false);
						btnIncluir.setText("Alterar");
						
						Cliente cliente = mediator.buscarCPF(text_cpf.getText().trim());
						
						text_nome_completo.setEnabled(true);
						grupoSexo.setEnabled(true);
						dataNascimento.setEnabled(true);
						text_renda.setEnabled(true);
						text_logradouro.setEnabled(true);
						text_numero.setEnabled(true);
						text_complemento.setEnabled(true);
						text_cep.setEnabled(true);
						text_cidade.setEnabled(true);
						combo_estado.setEnabled(true);
						btnIncluir.setEnabled(true);
						btnLimpar.setEnabled(true);
						btnVoltar.setEnabled(true);
						
						text_nome_completo.setText(cliente.getNomeCompleto());
						
						if(cliente.getSexo() == Sexo.MASCULINO) {
							btnMasculino.setSelection(true);
						}
						if(cliente.getSexo() == Sexo.FEMININO) {
							btnFeminino.setSelection(true);
						}
						
						dataNascimento.setText(cliente.getDataDeNascimento().toString());
						text_renda.setText(String.valueOf(cliente.getRenda()));
						text_logradouro.setText(cliente.getEndereco().getLogradouro());
						text_numero.setText(String.valueOf(cliente.getEndereco().getNumero()));
						text_complemento.setText(cliente.getEndereco().getComplemento());
						text_cep.setText(cliente.getEndereco().getCep());
						text_cidade.setText(cliente.getEndereco().getCidade());
						combo_estado.setText(cliente.getEndereco().getEstado());
					}
				}
			}
		});
		btnBuscar.setBounds(413, 10, 75, 25);
		btnBuscar.setText("Buscar");
		
		Label formatacao = new Label(shell, SWT.NONE);
		formatacao.setBounds(315, 119, 89, 28);
		formatacao.setText("dd/mm/aaaa");
		
		Group grupoSexo = new Group(shell, SWT.NONE);
		this.grupoSexo = grupoSexo;
		grupoSexo.setEnabled(false);
		grupoSexo.setBounds(115, 73, 311, 31);
		
		Label lblSexo = new Label(grupoSexo, SWT.NONE);
		lblSexo.setBounds(0, 10, 55, 16);
		lblSexo.setText("Sexo");
		
		Button btnMasculino = new Button(grupoSexo, SWT.RADIO);
		this.btnMasculino = btnMasculino;
		btnMasculino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button source =  (Button) e.getSource();
				if(source.getSelection())  {
                    sexo = Sexo.MASCULINO;
                }
			}
		});
		btnMasculino.setBounds(119, 10, 90, 16);
		btnMasculino.setText("Masculino");
		
		Button btnFeminino = new Button(grupoSexo, SWT.RADIO);
		this.btnFeminino = btnFeminino;
		btnFeminino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button source =  (Button) e.getSource();
				if(source.getSelection())  {
					sexo = Sexo.FEMININO;
                }
			}
		});
		btnFeminino.setText("Feminino");
		btnFeminino.setBounds(215, 10, 90, 16);
		
		Button btnIncluir = new Button(shell, SWT.NONE);
		this.btnIncluir = btnIncluir;
		btnIncluir.setEnabled(false);
		btnIncluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
								
				String CPF = text_cpf.getText().trim();
				String nomeCompleto = text_nome_completo.getText().trim();
				String nome_data_nascimento = dataNascimento.getText().trim();
				String rendaStr = text_renda.getText().trim();
				String logradouro = text_logradouro.getText().trim();
				String numero = text_numero.getText().trim();
				String complemento = text_complemento.getText().trim();
				String cep = text_cep.getText().trim();
				String cidade = text_cidade.getText().trim();
				String estado = combo_estado.getText().trim();
				
				
				if(!checkFormatDate(nome_data_nascimento)) {
					labelErro.setVisible(true);
					labelErro.setText("Formato do campo 'Data de nascimento' inválido!");
					return;
				}
				else if(numero.length() > 7) {
					labelErro.setVisible(true);
					System.out.println(numero);
					labelErro.setText("Formato do campo 'número' inválido!");
					return;
				}
				else if(cep.length() != 8) {
					labelErro.setVisible(true);
					labelErro.setText("Formato do campo 'CEP' inválido!");
					return;
				}
				
				int i = 0;
				while(i<rendaStr.length()) {
					if(!Character.isDigit(rendaStr.charAt(i)) && rendaStr.charAt(i) != '.'){
						labelErro.setVisible(true);
						labelErro.setText("Formato do campo 'Renda' inválido!");
						return;
					}
					i++;	
				}
				
				
				Date dataDeNascimento = criarData(nome_data_nascimento);
				Double renda = Double.parseDouble(rendaStr);
				Endereco endereco = criarEndereco(logradouro, numero, complemento, cep, cidade, estado);
				
				Cliente cliente = criarCliente(CPF, nomeCompleto, sexo, dataDeNascimento, renda, endereco);
				
				labelErro.setVisible(true);
				if(mediator.incluir(cliente).getNumeroFidelidade() == 0) {
					String erro = mediator.incluir(cliente).getMensagemErroValidacao();
					labelErro.setText(erro);
				}
				else {
					String sucesso = "Cadastrado com sucesso!";
					labelErro.setText(sucesso);
				}
				
				//System.out.println(mediator.incluir(cliente).getMensagemErroValidacao());
				//System.out.println(mediator.incluir(cliente).getNumeroFidelidade());
				//System.out.println(cliente.getNomeCompleto());
			}
		});

		btnIncluir.setBounds(242, 391, 103, 25);
		btnIncluir.setText("Incluir/Alterar");
		
		Label labelErro = new Label(shell, SWT.WRAP);
		this.labelErro = labelErro;
		labelErro.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		labelErro.setBounds(10, 396, 194, 31);
		labelErro.setVisible(false);
		
		Label labelErroCpf = new Label(shell, SWT.WRAP);
		this.labelErroCpf = labelErroCpf;
		labelErroCpf.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		labelErroCpf.setBounds(10, 15, 99, 31);
		labelErroCpf.setVisible(false);
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		this.btnLimpar = btnLimpar;
		btnLimpar.setEnabled(false);
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_nome_completo.setText("");
				
				btnMasculino.setSelection(false);
				btnFeminino.setSelection(false);
				
				dataNascimento.setText("");
				text_renda.setText("");
				text_logradouro.setText("");
				text_numero.setText("");
				text_complemento.setText("");
				text_cep.setText("");
				text_cidade.setText("");
				combo_estado.setText("");
			}
		});
		btnLimpar.setBounds(351, 391, 75, 25);
		btnLimpar.setText("Limpar");
		
		Button btnVoltar = new Button(shell, SWT.NONE);
		this.btnVoltar = btnVoltar;
		btnVoltar.setEnabled(false);
		btnVoltar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_cpf.setText("");
				text_nome_completo.setText("");
				
				btnMasculino.setSelection(false);
				btnFeminino.setSelection(false);
				
				dataNascimento.setText("");
				text_renda.setText("");
				text_logradouro.setText("");
				text_numero.setText("");
				text_complemento.setText("");
				text_cep.setText("");
				text_cidade.setText("");
				combo_estado.setText("");
				
				text_cpf.setEnabled(true);
				btnNovo.setEnabled(true);
				btnBuscar.setEnabled(true);
				btnIncluir.setText("Incluir/Alterar");
				
				text_nome_completo.setEnabled(false);
				grupoSexo.setEnabled(false);
				dataNascimento.setEnabled(false);
				text_renda.setEnabled(false);
				text_logradouro.setEnabled(false);
				text_numero.setEnabled(false);
				text_complemento.setEnabled(false);
				text_cep.setEnabled(false);
				text_cidade.setEnabled(false);
				combo_estado.setEnabled(false);
				btnIncluir.setEnabled(false);
				btnLimpar.setEnabled(false);
				btnVoltar.setEnabled(false);
				
			}
		});
		btnVoltar.setBounds(432, 391, 75, 25);
		btnVoltar.setText("Voltar");
		
		

	}
	Cliente criarCliente(String CPF, String nomeCompleto, Sexo sexo, Date dataDeNascimento, Double renda, Endereco endereco){
		
		return new Cliente(CPF, nomeCompleto, sexo, dataDeNascimento, renda, endereco);
	}
	
	Endereco criarEndereco(String logradouro, String numero, String complemento, String cep, String cidade, String estado) {
		int n1 = Integer.parseInt(numero);
		return new Endereco(logradouro, n1, complemento, cep, cidade, estado, "Brasil");
	}
	
	@SuppressWarnings("deprecation")
	Date criarData(String data) {
		String[] partes = data.split("/"); //returns an array with the 2 parts
		int dia = Integer.parseInt(partes[0]);
		int mes = Integer.parseInt(partes[1]);
		int ano = Integer.parseInt(partes[2]);
		return new Date(dia, mes, ano);
	}
	
	boolean checkFormatDate(String data) {
		if(data.charAt(2) != '/' || data.charAt(5) != '/' || data.length() != 10) {
			return false;
		}
		
		int i = 0;
		while(i<10) {
			
			if(i == 2 || i == 5) {
				i++;
				continue;
			}
			
			if(!Character.isDigit(data.charAt(i))) {
				return false;	
			}
			
			i++;
		}
		
		return true;
	}
}

