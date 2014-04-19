package com.codigomestre.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

	private ServerSocket servidor;
	public static final int PORTA = 12455;
	
	private Thread t;
	
	public void desconectar() throws IOException {
		servidor.close();
		t.stop();
	}
	
	/**
	 * Apaga valores de todas as tabelas no banco.
	 */
	public void reset() {
		
	}



	@Override
	public void run() {
		try {
			servidor = new ServerSocket(PORTA);

		} catch (Exception e) {
			System.out.println("Não foi possível inicializar o servidor!");
			System.exit(1);
		}

		while (true) {
			try {
				Socket clienteAtual = servidor.accept();
				t = new Thread(new ServidorSuporte(clienteAtual));
				t.start();
			} catch (IOException ioe) {
			}

		}
	}
}