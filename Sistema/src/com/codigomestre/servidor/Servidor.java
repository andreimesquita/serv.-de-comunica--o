package com.codigomestre.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.codigomestre.model.Salas;

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
			
		} catch (Exception e) {
			System.out.println("Não foi possível inicializar o servidor!");
			System.exit(1);
		}
		
		while (true) {
			try {
				Socket clienteAtual = servidor.accept();
				new ServidorSuporte(clienteAtual);
				// ((ObjectOutputStream) clienteAtual.getOutputStream()).writeObject("metodo run!!!");
				
			} catch(IOException ioe) {}
			
		}
		
	}

	public void desconectar() throws IOException {
		thread.stop();
		servidor.close();
		
	}
	/**
	 * Apaga valores de todas as tabelas no banco.
	 */
	public void reset() {
		// TODO codificar	
	}
}