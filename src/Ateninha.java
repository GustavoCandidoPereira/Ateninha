import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io. *;

public class Ateninha extends JPanel {
	/*
	 * 
	 * by: Cauan de Paula, Gustavo Candido e Heleno Matos - 4SIT
	 * 
	 */
	static String nome = "";
	static JPanel tela, NOTA, panelP;
	private static JLabel lbcodprodutos, lbitens, lbqtdprodutos, lbprecoprodutos, lblogo, lbsecretaria,lbprofessores;
	private JComboBox cbItens, cbProfessores;
	private FileDialog fdSalvar, fdAbrir;
	private JSpinner qtd;
	private SpinnerModel nP = new SpinnerNumberModel(1, 0, 30, 1);
	private JTextField tfCodigo, tfPreco;
	private static ImageIcon logo, secretaria;
	private static JButton btfinalizar;
	private JButton btentregue;
	private JButton btpendente;
	private JButton btAdicionar;
	private JButton btRemover;
	private static JScrollPane scrollTable;
	public static JTable table;
	private JPanel pnTable;
	public String nome_do_arquivo;
	public TextArea taTexto;

	DecimalFormat df = new DecimalFormat("###.00");
	static Color azul = new Color(65, 130, 180);

	static DecimalFormat formatador = new DecimalFormat("###,###.00");

	public Ateninha() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {

		setBackground(azul);
		setLayout(null);
		tela = new JPanel((null));

		String[] cbItensSecretaria = { "", "Caixa de Som Multilaser", "Apresentador de Slides", "Conversor VGA-HDMI",
				"Gravador CD/DVD", "Caixa de Som", "Projetor Epson", "Projetor Epson Branco", "Conversor HDMI-VGA",
				"Conversor Displayport HDMI", "Kit aula (câmera/tripé)", "Kit aula (câmera/tripé 5metros)", "Salas" };// vetor
																														// para
																														// a
																														// lista
																														// que
																														// irá
																														// no
																														// combo
		cbItens = new JComboBox(cbItensSecretaria); // adiciona o vetor a lista combobox

		String[] cbProf = { "", "ADRIANO COTA - DOCENTE", "ADRIANO JÚNIO - DOCENTE", "ALEF JESUS - DOCENTE",
				"ALISON ROCHA - DOCENTE", "ALLAN ARAÚJO - DOCENTE", "ANDERSON CARDOSO - DOCENTE",
				"ANDERSON FERREIRA - DOCENTE", "AUGUSTO NUNES - DOCENTE", "BRUNO SERAFIM - DOCENTE",
				"CARLOS ALBERTO - DOCENTE", "CLÉVER DEMÉTRIO - DOCENTE", "FELLIPE LEITE - DOCENTE",
				"FERNANDO SALLES - DOCENTE", "GILSON EDUARDO - DOCENTE", "HUGO PEREIRA - DOCENTE",
				"JOÃO BOSCO - DOCENTE", "JORGE EDUARDO - DOCENTE", "MARCO ANTONIO - DOCENTE",
				"MAURÍCIO BORGES - DOCENTE", "MAURÍCIO HIGA - DOCENTE", "MOISÉS SALLES - DOCENTE",
				"PAULO HENRIQUE - DOCENTE", "RAFAEL SASAKI - DOCENTE", "RICARDO SLUSSAREK - DOCENTE",
				"RODOLFO CÉSAR - DOCENTE", "RONALDO CESAR - DOCENTE", "SUNAO KOBAYASHI - DOCENTE",
				"VINICIUS MATOS - DOCENTE", "WAGNER - DOCENTE", "VICTOR - SUPORTE", "YURI - SUPORTE",
				"MARCOS - MANUTENÇÃO", "RICARDO SEIJI - DOCENTE", "PETER - COLÉGIO", "MARCELO - COLÉGIO",
				"ROBERTO UEDA - DIRETOR COLÉGIO", "JOSÉ ROBERTO - DIRETOR COLÉGIO", "FELIPE GUSTAVO - PROF COLÉGIO",
				"DIEGO - PROF COLÉGIO", "AFONSO - COLÉGIO", "BRUNO - PROF COLÉGIO", "LUIS MANGLANO - DIRETOR",
				"PAULO HENRIQUE NEIVA - DIRETOR", "FABRÍCIO FIUZA - FINANCEIRO", "LUCAS TENÓRIO - FINANCEIRO" };// vetor
																												// para
																												// a
																												// lista
																												// que
																												// irá
		cbProfessores = new JComboBox(cbProf);

		qtd = new JSpinner(nP);
		tfCodigo = new JTextField();
		tfPreco = new JTextField();
		
		taTexto = new TextArea();
		qtd.setBounds(329, 135, 55, 20);

		lbitens = new JLabel("Item/Sala:");
		lbcodprodutos = new JLabel("Código:");
		lbqtdprodutos = new JLabel("Quantidade:");
		lbprecoprodutos = new JLabel("Horário:");
		lbprofessores = new JLabel("Professores:");
		btfinalizar = new JButton("Encerrar período");

		logo = new ImageIcon("imagens//atena.png");
		lblogo = new JLabel(logo);
		lblogo.setBounds(20, 0, 250, 110);
		secretaria = new ImageIcon("imagens//secretaria.png");
		lbsecretaria = new JLabel(secretaria);
		lbsecretaria.setBounds(500, 10, 253, 100);

		btAdicionar = new JButton("Adicionar");
		btAdicionar.setToolTipText("Adiciona o item/sala selecionado");
		btRemover = new JButton("Remover");
		btRemover.setToolTipText("Remove o item/sala selecionado");
		btpendente = new JButton("Pendente");
		btpendente.setToolTipText("O item/sala está pendente");
		btentregue = new JButton("Entregue");
		btentregue.setToolTipText("O item/sala foi entregue");

		cbItens.setBounds(109, 135, 190, 20);
		cbProfessores.setBounds(520, 135, 190, 20);
		tfCodigo.setBounds(25, 135, 55, 20);
		tfPreco.setBounds(413, 135, 75, 20);

		btfinalizar.setBounds(530, 390, 200, 30);
		btAdicionar.setBounds(25, 160, 230, 25);
		btRemover.setBounds(260, 160, 230, 25);
		btentregue.setBounds(520, 190, 200, 25);
		btpendente.setBounds(520, 230, 200, 25);

		lbcodprodutos.setBounds(31, 115, 100, 20);
		lbitens.setBounds(180, 115, 80, 20);
		lbqtdprodutos.setBounds(320, 115, 80, 20);
		lbprecoprodutos.setBounds(430, 115, 150, 20);
		lbprofessores.setBounds(580, 115, 150, 20);

		cbItens.setBackground(Color.white);
		cbProfessores.setBackground(Color.white);
		lbcodprodutos.setFont(new Font("Arial", Font.BOLD, 12));
		lbcodprodutos.setForeground(Color.black);
		lbitens.setFont(new Font("Arial", Font.BOLD, 12));
		lbitens.setForeground(Color.black);
		lbqtdprodutos.setFont(new Font("Arial", Font.BOLD, 12));
		lbqtdprodutos.setForeground(Color.black);
		lbprecoprodutos.setFont(new Font("Arial", Font.BOLD, 12));
		lbprecoprodutos.setForeground(Color.black);
		lbprofessores.setFont(new Font("Arial", Font.BOLD, 12));
		lbprofessores.setForeground(Color.black);

		btAdicionar.setBackground(Color.black);
		btAdicionar.setForeground(Color.white);
		btAdicionar.setFont(new Font("AlNile", Font.BOLD, 12));

		btRemover.setBackground(Color.black);
		btRemover.setForeground(Color.white);
		btRemover.setFont(new Font("AlNile", Font.BOLD, 12));

		btfinalizar.setBackground(Color.black);
		btfinalizar.setForeground(Color.white);
		btfinalizar.setToolTipText("Encerra e cria um relatório em txt da tabela");
		btfinalizar.setFont(new Font("AlNile", Font.BOLD, 14));

		btentregue.setBackground(Color.black);
		btentregue.setForeground(Color.white);
		btentregue.setFont(new Font("AlNile", Font.BOLD, 12));

		btpendente.setBackground(Color.black);
		btpendente.setForeground(Color.white);
		btpendente.setFont(new Font("AlNile", Font.BOLD, 12));
		// tela
		Border lineBorder = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(lineBorder, "ATENINHA - Secretaria");
		title.setTitleColor(Color.black);
		tela.setBorder(title);
		tela.setBounds(10, 10, 762, 470);
		tfCodigo.setBorder(lineBorder);
		tfCodigo.setBackground(azul);
		cbProfessores.setBorder(lineBorder);
		cbProfessores.setBackground(azul);
		qtd.setBorder(lineBorder);
		qtd.setBackground(azul);
		tfPreco.setBorder(lineBorder);
		tfPreco.setBackground(azul);
		cbItens.setBorder(lineBorder);
		cbItens.setBackground(azul);
		
		tela.add(tfCodigo);
		tela.add(cbItens);
		tela.add(cbProfessores);
		tela.add(qtd);
		tela.add(tfPreco);

		tela.add(lblogo);
		tela.add(lbsecretaria);
		tela.add(lbcodprodutos);
		tela.add(lbitens);
		tela.add(lbqtdprodutos);
		tela.add(lbprecoprodutos);
		tela.add(lbprofessores);

		tela.add(btAdicionar);
		tela.add(btRemover);
		tela.add(btpendente);
		tela.add(btentregue);
		add(btfinalizar);

		pnTable = new JPanel(new BorderLayout());
		pnTable.setBorder(new TitledBorder("Secretaria"));// linhas 64 e 65 definem a borda do painel , com o nome dele
															// (nesse caso itens do pedido)
		scrollTable = new JScrollPane();
		df.setMinimumFractionDigits(2);// minimo de numeros depois da virgula
		df.setMaximumFractionDigits(2);// minimo de núemros depois da virgula
		DefaultTableModel tableModel = new DefaultTableModel(
				new String[] { "Código", "Item/Sala", "Quantidade", "Horário", "Professores","Status"}, 0) {
			public boolean isCellEditable(int row, int col) { // método para dizer se a celula será editavel ou não
				if (col == 3 || col == 0) {
					return false;
				}
				return true;
			}
		};
		table = new JTable(tableModel);// instancia a tabela e adiciona o modelo de tabela criado anteriomente a ela
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.CENTER);// a variável alinhaDireita alinha o conteudo da
																	// tabela a direita
		table.getColumnModel().getColumn(0).setPreferredWidth(50); // define o tamanho da coluna
		table.getColumnModel().getColumn(0).setResizable(false);// define se a coluna é editavel ou não ;
		table.getColumnModel().getColumn(0).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(1).setPreferredWidth(120); // define o tamanho da coluna
		table.getColumnModel().getColumn(1).setResizable(true);// define se a coluna é editavel ou não ;
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);// alinha o conteúdo da ceula a direita;
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setCellRenderer(alinhaDireita);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// linhas 97 e 98 define que as colunas não podem ser
														// reorganizadas com arraste e solte
		scrollTable.setViewportView(table);
		pnTable.add(scrollTable);
		pnTable.setBounds(25, 190, 464, 250);
		tela.add(pnTable);
		pnTable.setBackground(azul);
		tela.setBackground(Color.GRAY);

		add(tela);
	}

//Verificar os produtos no definir eventos e colocar as imagens de cada um, no JOPtion Pane que vai ser a "nota fiscal"
	public void definirEventos() {
		btAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();

				if (tfCodigo.getText().equals("") || cbItens.getSelectedItem().equals("")|| qtd.getValue().toString().equals("") || tfPreco.getText().equals("")|| cbProfessores.getSelectedItem().equals("")) {
					UIManager.getDefaults().put("OptionPane.background", new Color(65, 130, 180));
					UIManager.put("Panel.background", new Color(65, 130, 180));
					JOptionPane.showMessageDialog(pnTable, "Preencha todos os campos", "Erro", 0);
					return;
				}
				table.getSelectedRows();
				for (int linha = 0; linha < table.getRowCount(); linha++) { 
					
					if (tfCodigo.getText().equals(table.getValueAt(linha, 0))
							&& cbItens.getSelectedItem().equals(table.getValueAt(linha, 1)) == false) {
						UIManager.getDefaults().put("OptionPane.background", new Color(65, 130, 180));
						UIManager.put("Panel.background", new Color(65, 130, 180));
						JOptionPane.showMessageDialog(pnTable,
								"Não é possivel cadastrar um mesmo código duas vezes em itens diferentes.", "Erro", 0);
						return;
					}
				}

				dtm.addRow(new Object[] { tfCodigo.getText(), cbItens.getSelectedItem(), qtd.getValue(),
						tfPreco.getText(), cbProfessores.getSelectedItem(),"Com professor"});// linhas 114 a 116 adicionam uma linha na
																				// tabela
				limparCampos();
			}

		});
		btRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] linhas = table.getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				for (int i = (linhas.length - 1); i >= 0; --i) { // esse for percorre o vetor de linhas , verifica qual
																	// linha é selecionada e exclui
					dtm.removeRow(linhas[i]);
				}
			}
		});
		btfinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() == 0) {
					UIManager.getDefaults().put("OptionPane.background", new Color(65, 130, 180));
					UIManager.put("Panel.background", new Color(65, 130, 180));
					JOptionPane.showMessageDialog(pnTable, "Tabela vazia", "Erro", 0);
					return;
				}else {
				/*UIManager.getDefaults().put("OptionPane.background", new Color(65, 130, 180));
				UIManager.put("Panel.background", new Color(65, 130, 180));
				UIManager.put("Button.background", Color.BLACK);
				UIManager.put("Button.foreground", Color.white);
				UIManager.put("OptionPane.messageForeground", Color.white);
				UIManager.put("OptionPane.yesButtonText", "Sim");
				UIManager.put("OptionPane.noButtonText", "Não");
				int resposta = JOptionPane.showConfirmDialog(null,
						"Você já possui algum diretório para salvar os relatórios?", "Ateninha - Gerenciador by: CGH",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int resp1 = JOptionPane.showConfirmDialog(null,
							"Você utiliza o local padrão (Área de Trabalho) para salvar os relatórios?",
							"Ateninha - Gerenciador by: CGH", JOptionPane.YES_NO_OPTION);
					if (resp1 == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null,
								"Ok, salvando relatório em 'Ateninnha Relatórios' na Área de Trabalho.");
						pegarTexto2();
					} else {
						String caminho = JOptionPane.showInputDialog(null,
								"Digite o caminho para criar um novo diretório: ");
						String nome = "Ateninnha Relatórios";
						File dir = new File(caminho + "/" + nome);
						if (!dir.exists()) {
							dir.mkdir();
						}
						JOptionPane.showMessageDialog(null, "Diretório criado: " + caminho + "/" + nome);
					}
				} else {
					String caminho = JOptionPane.showInputDialog(null,
							"Digite o caminho para criar um novo diretório: ");
					String nome = "Ateninnha Relatórios";
					File dir = new File(caminho + "/" + nome);
					if (!dir.exists()) {
						dir.mkdir();
					}
					JOptionPane.showMessageDialog(null, "Diretório criado: " + caminho + "/" + nome);
				}*/
				pegarTexto2();}
			}
		});
		btpendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] linhas = table.getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				for (int i = (linhas.length - 1); i >= 0; --i) { // esse for percorre o vetor de linhas , verifica qual
					table.setValueAt("Pendente", linhas[i], 5);										// linha é selecionada e exclui
					}
			}
		});
		btentregue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] linhas = table.getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				for (int i = (linhas.length - 1); i >= 0; --i) { // esse for percorre o vetor de linhas , verifica qual
														// linha é selecionada e exclui
					table.setValueAt("Entregue", linhas[i], 5);
				}
			}
		});
	}
	

	private void pegarTexto2() {
		 Atividade_word teste = new Atividade_word();
		 teste.pegarTexto();
	}
    public void pegarTela() {
    	JFrame frame = new JFrame("Ateninha - Gerenciador by: CGH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Ateninha());
		frame.setBounds(420, 70, 800, 530);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		Image Icone = Toolkit.getDefaultToolkit().getImage("imagens//logoat.png");
		frame.setIconImage(Icone);
	}

	private void limparCampos() {
		cbItens.setSelectedIndex(0);
		cbProfessores.setSelectedIndex(0);
		tfCodigo.setText("");
		qtd.setValue(1);
		tfPreco.setText("");
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Ateninha - Gerenciador by: CGH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Ateninha());
		frame.setBounds(420, 70, 800, 530);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		Image Icone = Toolkit.getDefaultToolkit().getImage("imagens//logoat.png");
		frame.setIconImage(Icone);
	}
}