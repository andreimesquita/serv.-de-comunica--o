package com.codigomestre.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	
	private Thread thread;
	private ServerSocket servidor;
	public static final int PORTA = 12455;
	
	public Servidor() {
		thread = new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args) {
		new Servidor();
	}
	
	@Override
	public void run() {
		
		try {
			servidor = new ServerSocket(PORTA);
			System.out.println("ENDEREÇO: " + servidor.getInetAddress().getHostAddress());
			
		} catch (Exception e) {
			System.out.println("Não foi possível inicializar o servidor!");
			System.exit(1);
		}
		
		while (true) {
			try {
				Socket clienteAtual = servidor.accept();
				new ServidorSuporte(clienteAtual);
				System.out.println("O usuario foi conectado......");
				// ((ObjectOutputStream) clienteAtual.getOutputStream()).writeObject("metodo run!!!");
				
			} catch(IOException ioe) {}
			
		}
		
	}

	public void desconectar() throws IOException {
		thread.stop();
		servidor.close();
		
	}
}