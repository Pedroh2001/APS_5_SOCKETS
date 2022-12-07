package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.awt.event.ActionEvent;

public class abertura extends JFrame {

	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		abertura frame = new abertura();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public abertura() {
		File file = new File("C:\\Relatorios"); 
		boolean f = file.mkdir();  
		if(f){  
			System.out.println("Gerada pasta");  
		}else{  
			System.out.println("Erro / pasta existente");  
		}

		setTitle("Secretaria de Estado do Meio Ambiente");
		setResizable(false);
		setLocationRelativeTo(null);
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");  
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");

		//op��es na sele��o do bot�o X
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int ret = JOptionPane.showConfirmDialog (abertura.this, "Deseja encerrar?");
				if (ret == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else if (ret == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 840, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel servidorLb = new JLabel("Servidor chat:");
		servidorLb.setHorizontalAlignment(SwingConstants.CENTER);
		servidorLb.setBounds(711, 10, 93, 37);
		servidorLb.setOpaque(true);
		panel.add(servidorLb);

		JLabel servidorColor = new JLabel("");
		servidorColor.setOpaque(true);
		servidorColor.setHorizontalAlignment(SwingConstants.CENTER);
		servidorColor.setBounds(711, 48, 93, 37);
		panel.add(servidorColor);

		try (ServerSocket ss = new ServerSocket(54320)){
			servidorColor.setBackground(Color.red);
			servidorColor.setText("OFF");
			ss.close();
		} catch (Exception e) {
			servidorColor.setBackground(Color.green);
			servidorColor.setText("ON");
		}

		JLabel lblNewLabel = new JLabel("Secretaria de Estado do Meio Ambiente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 691, 27);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sistema para troca de informações e relatórios sobre atividades industriais poluentes que afetam o Rio Tietê");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 37, 692, 27);
		panel.add(lblNewLabel_1);

		JButton btnChat = new JButton("Abrir Chat");
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatServer.server1.start();
				chatServer.server2.start();

				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							clientChat c = new clientChat();
							c.iniciarChat();
						} catch (ClassNotFoundException | IOException e) {
							System.out.println("Erro ao criar cliente");
						}
						super.run();
					}
				};
				t.start();
				dispose();
			}
		});
		btnChat.setBounds(10, 96, 125, 37);
		panel.add(btnChat);

		JButton btnRelatorios = new JButton("Relatórios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser sel = new JFileChooser("C:\\Relatorios");
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo de texto .txt","txt");
				sel.setFileFilter(filtro);
				sel.setDialogTitle("Selecione o arquivo .txt");
				sel.setVisible(true);
				File file = new File("");
				String texto  = ""; 

				int resposta = sel.showOpenDialog(new JDialog());

				if (resposta == JFileChooser.APPROVE_OPTION) {
					file = sel.getSelectedFile();
					texto = model.methods.leitorTxt(file.getPath());
					JOptionPane.showMessageDialog(null, "Arquivo carregado com sucesso");
					visualizadorRelatorio aux = new visualizadorRelatorio(texto);
					aux.setVisible(true);
				}
				else if (resposta == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Cancelado! Selecione a opcao novamente");
				}
			}
		});
		btnRelatorios.setBounds(10, 144, 125, 37);
		panel.add(btnRelatorios);

		JButton btnSobre = new JButton("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sobre s = new sobre();
				s.setVisible(true);
				s.setLocationRelativeTo(null);
			}
		});
		btnSobre.setBounds(10, 192, 125, 37);
		panel.add(btnSobre);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(679, 240, 125, 37);
		panel.add(btnSair);

		JLabel lblNewLabel_2 = new JLabel(" Entrar no chat para conversar com as equipes de inspetores conectadas e elaborar relatórios");
		lblNewLabel_2.setBackground(Color.GRAY);
		lblNewLabel_2.setBounds(145, 96, 659, 37);
		lblNewLabel_2.setOpaque(true);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel(" Acesso aos relatórios já elaborados");
		lblNewLabel_2_1.setBackground(Color.GRAY);
		lblNewLabel_2_1.setBounds(145, 144, 659, 37);
		lblNewLabel_2_1.setOpaque(true);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel(" Informações sobre a aplicação e seu objetivo");
		lblNewLabel_2_2.setBackground(Color.GRAY);
		lblNewLabel_2_2.setBounds(145, 192, 659, 37);
		lblNewLabel_2_2.setOpaque(true);
		panel.add(lblNewLabel_2_2);		
	}
}
