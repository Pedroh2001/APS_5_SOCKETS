package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import model.relatorio;

public class manipuladorConversa extends Thread {
	Socket usuarioConversa;
	BufferedReader entrada;
	PrintWriter saida;
	String nome;

	public manipuladorConversa (Socket usuarioConversa) throws IOException {
		this.usuarioConversa = usuarioConversa;
	}

	public void run() {
		try {
			entrada = new BufferedReader(new InputStreamReader(usuarioConversa.getInputStream()));
			saida = new PrintWriter(usuarioConversa.getOutputStream(), true);

			int contador = 0;

			while (true) {
				if(contador > 0) {
					saida.println("Nome Existente");
				}else {
					saida.println("Nome Requerido");
				}

				nome = entrada.readLine();

				if (nome == null) {
					return;
				}

				if (!chatServer.listaUsuarios.contains(nome)) {
					chatServer.listaUsuarios.add(nome);
					break;
				}
				contador++;
			}

			saida.println("Nome Aceito" + nome);
			chatServer.printWriters.add(saida);

			while (true) {
				String msg = entrada.readLine();

				if(msg == null) {
					return;
				}
				else if (msg.contains("#removeruser#")|| msg.equals("#removeruser#")) {
					String aux = msg.replace("#removeruser#", "");
					chatServer.listaUsuarios.remove(aux);
				}

				else if (msg.contains("#atualizar_clientes#")|| msg.equals("#atualizar_clientes#")) {
					saida.println("#atualizar_clientes#" + chatServer.listaUsuarios);
				}

				else {
					for (PrintWriter writer : chatServer.printWriters) {
						writer.println(nome + ": " + msg);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println("Erro no servidor: "+ e.getMessage());
		}

	}
}

class ManipuladorConversa1 extends Thread{ 
	Socket usuarioConversa;
	ObjectInputStream objEn; 
	ObjectOutputStream objSa;

	public ManipuladorConversa1 (Socket usuarioConversa) throws IOException {
		this.usuarioConversa = usuarioConversa; }

	public void run1() throws IOException, ClassNotFoundException { 
		objEn = new ObjectInputStream(usuarioConversa.getInputStream()); 
		objSa = new ObjectOutputStream(usuarioConversa.getOutputStream());

		relatorio entrada = (relatorio) objEn.readObject();
		entrada.getNome();

		while(true) {			
			if (entrada != null) { 
				JOptionPane.showMessageDialog(null, entrada.getInformacoes()); 
			} 
		} 
	} 
}



