package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.relatorio;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class relatorioV extends JFrame {

	private static final long serialVersionUID = 1L;
	static ObjectOutputStream objSa;
	private JPanel contentPane;
	private JTextField nomeText;
	private JTextField cnpjText;
	private JTextField nomeEmpText;

	public relatorioV(String qCriou) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int ret = JOptionPane.showConfirmDialog (relatorioV.this, "Deseja cancelar a criação do retório?");
				if (ret == JOptionPane.YES_OPTION) {
					dispose();
				}else if (ret == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		});
		
		setTitle("Relatório iniciado por: " + qCriou);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(950, 100, 640, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel1 = new JPanel();
		panel1.setBorder(null);
		panel1.setLayout(null);
		panel1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel1, BorderLayout.CENTER);

		JLabel nome = new JLabel("Nome do relatório:");
		nome.setForeground(Color.WHITE);
		nome.setBounds(10, 11, 108, 33);
		panel1.add(nome);

		JLabel descricao = new JLabel("Descrição:");
		descricao.setForeground(Color.WHITE);
		descricao.setBounds(10, 187, 108, 33);
		panel1.add(descricao);

		nomeText = new JTextField();
		nomeText.setBounds(128, 11, 475, 33);
		panel1.add(nomeText);
		nomeText.setColumns(10);

		JLabel data = new JLabel("Data:");
		data.setForeground(Color.WHITE);
		data.setBounds(10, 143, 108, 33);
		panel1.add(data);

		cnpjText = new JTextField(14);

		cnpjText.addKeyListener(new KeyAdapter() {
			@Override public void keyReleased(KeyEvent e) {
				cnpjText.setText(cnpjText.getText().replaceAll("[^0-9]","")); }
		});

		cnpjText.setColumns(10);
		cnpjText.setBounds(128, 99, 475, 33);
		panel1.add(cnpjText);

		nomeEmpText = new JTextField();
		nomeEmpText.setColumns(10);
		nomeEmpText.setBounds(128, 55, 475, 33);
		panel1.add(nomeEmpText);

		JLabel nomeEmp = new JLabel("Nome da empresa:");
		nomeEmp.setForeground(Color.WHITE);
		nomeEmp.setBounds(10, 55, 108, 33);
		panel1.add(nomeEmp);

		JLabel cnpj = new JLabel("CNPJ:");
		cnpj.setForeground(Color.WHITE);
		cnpj.setBounds(10, 99, 108, 33);
		panel1.add(cnpj);

		JLabel Informacoes = new JLabel("Informações:");
		Informacoes.setForeground(Color.WHITE);
		Informacoes.setBounds(10, 249, 108, 33);
		panel1.add(Informacoes);

		JEditorPane InformacoesText = new JEditorPane();
		InformacoesText.setBounds(127, 249, 476, 282);
		panel1.add(InformacoesText);

		JEditorPane descricaoText = new JEditorPane();
		descricaoText.setBounds(128, 187, 475, 52);
		panel1.add(descricaoText);

		JButton cancelbtn = new JButton("Cancelar");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbtn.setBounds(10, 584, 168, 36);
		panel1.add(cancelbtn);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboBox.addItem("1");comboBox.addItem("2");comboBox.addItem("3");comboBox.addItem("4");comboBox.addItem("5");comboBox.addItem("6");
		comboBox.addItem("7");comboBox.addItem("8");comboBox.addItem("9");comboBox.addItem("10");comboBox.addItem("11");comboBox.addItem("12");
		comboBox.addItem("13");comboBox.addItem("14");comboBox.addItem("15");comboBox.addItem("16");comboBox.addItem("17");comboBox.addItem("18");
		comboBox.addItem("19");comboBox.addItem("20");comboBox.addItem("21");comboBox.addItem("22");comboBox.addItem("23");comboBox.addItem("24");
		comboBox.addItem("25");comboBox.addItem("26");comboBox.addItem("27");comboBox.addItem("28");comboBox.addItem("29");comboBox.addItem("30");
		comboBox.addItem("31");
		comboBox.setBounds(128, 143, 150, 33);
		panel1.add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboBox_1.addItem("1");comboBox_1.addItem("2");comboBox_1.addItem("3");comboBox_1.addItem("4");comboBox_1.addItem("5");comboBox_1.addItem("6");
		comboBox_1.addItem("7");comboBox_1.addItem("8");comboBox_1.addItem("9");comboBox_1.addItem("10");comboBox_1.addItem("11");comboBox_1.addItem("12");
		comboBox_1.setBounds(289, 143, 155, 33);
		panel1.add(comboBox_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboBox_2.addItem("2022");
		comboBox_2.setBounds(454, 143, 150, 33);
		panel1.add(comboBox_2);
		InformacoesText.getText();

		JEditorPane obsText = new JEditorPane();
		obsText.setFont(new Font("Tahoma", Font.BOLD, 11));
		obsText.setForeground(Color.RED);
		obsText.setBounds(10, 540, 594, 33);
		obsText.setEditable(false);
		obsText.setOpaque(true);
		panel1.add(obsText);

		JButton savebtn = new JButton("Salvar Relatório");
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomeText.getText() != "" && nomeEmpText.getText()!= "" && cnpjText.getText()!= "" && descricaoText.getText() != "" &&
						InformacoesText.getText() != "" && cnpjText.getText().length() == 14) {

					Socket usuario = null;

					try {
						usuario = new Socket("127.0.0.1", 54321);
						relatorio rel = new relatorio(nomeText.getText(), descricaoText.getText(),(String.valueOf(comboBox.getSelectedItem())+ "/" 
								+ String.valueOf(comboBox_1.getSelectedItem())+ "/" + String.valueOf(comboBox_2.getSelectedItem())),
								nomeEmpText.getText(), cnpjText.getText(), InformacoesText.getText(), qCriou);
						objSa = new ObjectOutputStream(usuario.getOutputStream());
						relatorioV.objSa.writeObject(rel);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else { 
					JOptionPane.showMessageDialog(null, "Preencha todos os campos da forma correta!");
					obsText.setText(null);
					
					if (nomeText.getText().equals("")) {
						obsText.setText(obsText.getText() + " 'Nome do relatório inválido' ");
					}
					if (nomeEmpText.getText().equals("")) {
						obsText.setText(obsText.getText() + " 'Nome da empresa inválido' ");
					}
					if (cnpjText.getText().equals("") || cnpjText.getText().length() != 14) {
						obsText.setText(obsText.getText() + " 'CNPJ inválido' ");
					}
					if (descricaoText.getText().equals("")) {
						obsText.setText(obsText.getText() + " 'Descrição inválida' ");
					}
					if (InformacoesText.getText().equals("")) {
						obsText.setText(obsText.getText() + " 'Informações inválidas' ");
					}
				}
			}
		});
		savebtn.setBounds(436, 584, 168, 36);
		panel1.add(savebtn);
	}
}
