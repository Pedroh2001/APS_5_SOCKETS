package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class clientChat extends JFrame {

	private static final long serialVersionUID = 1L;
	static BufferedReader entrada;
	static PrintWriter saida;
	static ObjectInputStream objEn;
	static ObjectOutputStream objSa;
	private JPanel contentPane;	
	static JPanel panel = new JPanel();
	static JButton enviar = new JButton("Enviar");
	static JTextField inserir = new JTextField();
	static JTextArea conversa = new JTextArea();
	private final JScrollPane scroll = new JScrollPane(conversa);
	static JTextArea usuariosConectados = new JTextArea();
	static JButton btnUsuarios = new JButton("Atualizar Usuários");
	private final JLabel userCon = new JLabel();

	public clientChat() {

		//texto no bot�o do JOptionPane	
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");  
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");

		//op��es na sele��o do bot�o X
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int ret = JOptionPane.showConfirmDialog (clientChat.this, "Deseja encerrar o chat?");
				if (ret == JOptionPane.YES_OPTION) {
					saida.println("#removeruser#"+ userCon.getText());
					sobre aux = new sobre();
					aux.setVisible(true);
					dispose();
				}else if (ret == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		});

		setTitle("Chat");
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(50, 100, 885, 680);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		DefaultCaret caret = (DefaultCaret)conversa.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 

		panel.setBorder(null);
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientChat.inserir.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira um texto no campo!");
				}else {
					clientChat.saida.println(clientChat.inserir.getText());
					clientChat.inserir.setText("");
				}
			}
		});
		enviar.setBounds(596, 584, 77, 36);
		panel.add(enviar);
		inserir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (clientChat.inserir.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira um texto no campo!");
					}else {
						clientChat.saida.println(clientChat.inserir.getText());
						clientChat.inserir.setText("");
					}
				}
			}
		});

		inserir.setBounds(95, 584, 491, 36);
		inserir.setColumns(10);
		inserir.setEditable(false);
		panel.add(inserir);
		usuariosConectados.setBackground(Color.LIGHT_GRAY);
		usuariosConectados.setLineWrap(true);

		conversa.setBounds(10, 58, 617, 339);
		conversa.setLineWrap(true);
		userCon.setHorizontalTextPosition(SwingConstants.CENTER);
		userCon.setHorizontalAlignment(SwingConstants.CENTER);
		userCon.setBackground(Color.LIGHT_GRAY);
		userCon.setOpaque(true);
		userCon.setForeground(Color.BLACK);
		userCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userCon.setBounds(137, 11, 536, 36);
		userCon.setVisible(false);
		panel.add(userCon);

		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setAutoscrolls(true);
		scroll.setBounds(10, 58, 665, 518);
		conversa.setEditable(false);

		panel.add(scroll);

		JButton view_relatorios = new JButton("Ver relatórios");
		view_relatorios.addActionListener(new ActionListener() {
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
					aux.setLocationRelativeTo(null);
				}
				else if (resposta == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Cancelado! Selecione a opcao novamente");
				}
			}
		});
		view_relatorios.setBounds(682, 11, 168, 36);
		panel.add(view_relatorios);

		usuariosConectados.setBounds(682, 152, 168, 423);
		panel.add(usuariosConectados);

		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientChat.saida.println("#atualizar_clientes#");
			}
		});
		btnUsuarios.setBounds(682, 584, 168, 36);
		panel.add(btnUsuarios);

		JLabel connLb = new JLabel(" Conectado como:");
		connLb.setForeground(Color.BLACK);
		connLb.setBackground(Color.GRAY);
		connLb.setOpaque(true);
		connLb.setBounds(10, 11, 117, 36);
		panel.add(connLb);

		JButton view_relatorios_1 = new JButton("Criar relatório");
		view_relatorios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioV rela = new relatorioV(userCon.getText());
				rela.setVisible(true);
			}
		});
		view_relatorios_1.setBounds(682, 58, 168, 36);
		panel.add(view_relatorios_1);

		JLabel mensLb = new JLabel(" Mensagem:");
		mensLb.setOpaque(true);
		mensLb.setForeground(Color.BLACK);
		mensLb.setBackground(Color.GRAY);
		mensLb.setBounds(10, 584, 77, 36);
		panel.add(mensLb);

		JLabel lblUsuriosConectados = new JLabel(" Usuários conectados:");
		lblUsuriosConectados.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuriosConectados.setOpaque(true);
		lblUsuriosConectados.setForeground(Color.BLACK);
		lblUsuriosConectados.setBackground(Color.GRAY);
		lblUsuriosConectados.setBounds(682, 105, 168, 36);
		panel.add(lblUsuriosConectados);
	}

	public void iniciarChat() throws IOException, ClassNotFoundException{
		Socket usuario = new Socket("127.0.0.1", 54320);

		entrada = new BufferedReader(new InputStreamReader(usuario.getInputStream()));
		saida = new PrintWriter(usuario.getOutputStream(), true);

		String msg = "";

		while (msg != null) {
			msg = entrada.readLine();

			if (msg.equals("Nome Requerido")) {
				Thread t = new Thread() {
					String nome = "";
					public void run() {
						while (nome.equals("")) {
							nome = JOptionPane.showInputDialog("Informe um no"
									+ "me de usuário: ");		
						}
						saida.println(nome);
					};
				};
				t.start();
			}
			else if (msg.equals("Nome Existente")){
				String nome = JOptionPane.showInputDialog("Informe outro nome válido"
						+ " | nome em uso...");
				saida.println(nome);
			}
			else if (msg.contains("Nome Aceito")) {
				String aux ="";
				aux = msg.replace("Nome Aceito", "");
				userCon.setText(aux);
				userCon.setVisible(true);
				inserir.setEditable(true);
			}

			else if(msg.contains("encerrarchat")){
				saida.println("encerrarchat");
				conversa.append(msg + "\nChat encerrado...");
				usuario.close();
				msg = null;
				saida.println("encerrarchat");
				inserir.setText("Chat encerrado!");
				inserir.setEditable(false);
				enviar.setVisible(false);
			}
			else if (msg.contains("#atualizar_clientes#")) {
				String aux = "";
				aux = msg.replace("#atualizar_clientes#", "");
				aux = aux.replace("[", "");
				aux = aux.replace(" ", "");
				aux = aux.replace("]", "");
				aux = aux.replace(",", "\n");
				usuariosConectados.setText(null);
				usuariosConectados.append(aux);
			}
			else {
				conversa.append(msg.replace("##","") + "\n");
			}
		}
	}
}