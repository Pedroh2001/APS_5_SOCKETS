package view;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.lerObj;

public class chatServer {

	static ArrayList<String> listaUsuarios = new ArrayList<String>();
	static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

	static Thread server1 = new Thread(new Runnable() {

		@Override
		public void run(){
			ServerSocket ss = null;

			while(true) {
				try {
					if (ss == null) ss = new ServerSocket(54320);
					Socket socket = ss.accept();
					manipuladorConversa conversa = new manipuladorConversa(socket);
					conversa.start();

				} catch (Exception e) {
					try {
						ss.close();
					} catch (Exception e2) {
					};
				}
			}
		}
	});

	static Thread server2 = new Thread(new Runnable() {

		@Override
		public void run(){
			ServerSocket ss1 = null;

			while(true) {
				try {
					if (ss1 == null)ss1 = new ServerSocket(54321);
					Socket socket1 = ss1.accept();
					lerObj.lerObjeto(socket1);

				} catch (Exception e) {
					try {
						ss1.close();
					} catch (Exception e2) {
					};
				}
			}
		}
	});
}

