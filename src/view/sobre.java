package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sobre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public sobre() {
		setTitle("Sobre");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnVoltar = new JButton("Fechar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(497, 283, 125, 37);
		panel.add(btnVoltar);
		
		JTextPane txtpnDesejaSaberQuais = new JTextPane();
		txtpnDesejaSaberQuais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDesejaSaberQuais.setText("Devido aos níveis de poluição crescente do Rio Tietê desde sua nascente em Salesópolis (SP)"
				+ " até a sua passagem pela região da grande São Paulo, a Secretaria de Estado do Meio Ambiente deseja saber quais"
				+ " atividades industriais estão gerando tal poluição.\n\nEsta aplicação foi desenvolvida para trocar informações das"
				+ " equipes de inspetores treinados e capacitados que se revezarão dentro de cada indústria para obtenção de"
				+ " informações, controlando os processos e passando informações online para a Secretaria, possibilitando a análise"
				+ " para verificar quais empresas estão seguindo práticas irregulares.");
		txtpnDesejaSaberQuais.setBounds(10, 11, 612, 261);
		txtpnDesejaSaberQuais.setEditable(false);
		panel.add(txtpnDesejaSaberQuais);
	}
}
