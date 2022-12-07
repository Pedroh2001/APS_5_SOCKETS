package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class lerObj {

	public static void lerObjeto (Socket socket) {
		Thread t = new Thread(new Runnable() {
			Socket socket1 = socket;
			ObjectInputStream objEn; 

			@Override
			public void run() {
				while (true) {
					try {
						objEn = new ObjectInputStream(socket1.getInputStream()); ;
						relatorio entrada = (relatorio) objEn.readObject();
						String nome = entrada.getNome();					
						String local = ("C:\\Relatorios\\" + nome + ".txt");
						try(FileWriter criadorDeTxt = new FileWriter(local);
								BufferedWriter buffer = new BufferedWriter(criadorDeTxt);
								PrintWriter escritorDeTxt = new PrintWriter(buffer);)
						{
							escritorDeTxt.append(entrada.getAll());
							JOptionPane.showMessageDialog(null, "Arquivo gerado com sucessso");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Erro ao gerar arquivo");
						}
					}catch (Exception e) {
						System.out.println("Sem possibilidade");
						break;
					}}}
		});
		t.start();
	}}
