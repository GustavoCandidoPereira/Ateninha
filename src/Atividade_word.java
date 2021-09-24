import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io. *;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


/*

by: Cauan de Paula, Gustavo Candido e Heleno Matos - 4SIT

*/



public class Atividade_word extends JFrame {
	public FileDialog fdAbrir, fdSalvar;
	public String nome_do_arquivo;
	public JTable table = Ateninha.table;
	public JPanel tela = Ateninha.tela;
	String apoio = "";

	public static void main(String args[]) {
		Ateninha arroz = new Ateninha();
		arroz.pegarTela();
}

	public Atividade_word() {
		inicializarComponentes();
	}

	public void inicializarComponentes() {
		setLayout(null);
		setBounds(250, 50, 640, 300);
		setResizable(false);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		
	}
	public void pegarTexto() {
		String [] prof = new String[20];
		String [] item = new String[20];
		String [] cod = new String[20];
		String [] qtd = new String[20];
		String [] hora = new String[20];
		String [] sts = new String[20];
		String [] descri = new String[20];;
		UIManager.getDefaults().put("OptionPane.background", new Color(65, 130, 180));
		UIManager.put("Panel.background", new Color(65, 130, 180));
		UIManager.put("Button.background", Color.BLACK);
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("OptionPane.messageForeground", Color.white);
		
		
		try {
			String usa = JOptionPane.showInputDialog(null,"Digite o nome de usuário do seu computador: ");
			String caminho = "C:/Users/"+usa+"/Desktop";
			String nome = "Ateninnha_Relatórios";
			File dir = new File(caminho + "/" + nome);
			if (!dir.exists()) {
				dir.mkdir();
			}
			nome_do_arquivo = dir+"/Relatório.txt";
			FileWriter out = new FileWriter(nome_do_arquivo);

			table.getSelectedRows();
			for (int linha = 0; linha < table.getRowCount(); linha++) {
				prof[linha] = table.getValueAt(linha,4).toString();
				item[linha] = table.getValueAt(linha,1).toString();
				qtd[linha] = table.getValueAt(linha,2).toString();
				hora[linha] = table.getValueAt(linha,3).toString();
				cod[linha] = table.getValueAt(linha,0).toString();
				sts[linha] = table.getValueAt(linha,5).toString();
				descri[linha] = "Professor: " + prof[linha]+ "\r\n   Código: "+ cod[linha]+ "\r\n   Item/Sala: " + item[linha] + "\r\n   Quantidade: "+qtd[linha]+ "\r\n   Horário: "+hora[linha]+"\r\n   Status: "+sts[linha];
				apoio = apoio+descri[linha]+"\r\n";			
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			out.write("Relatório feito em: "+dtf.format(now)+"\r\n"+apoio);
			out.close();
			JOptionPane.showMessageDialog(null, "Relatório salvo em 'Ateninnha_Relatórios' na sua Área de Trabalho.");
		} catch (IOException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar relatório. " + erro.toString());
		}
	}
}
