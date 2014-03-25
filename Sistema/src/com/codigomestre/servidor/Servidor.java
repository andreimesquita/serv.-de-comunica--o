package com.codigomestre.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

public class Servidor implements Runnable {
	
	private Thread t;
	private ServerSocket s;
	public static final String MENSAGEM_SUCESSO_ATIVAR = "Servidor iniciado com sucesso!";
	public static final String MENSAGEM_ERRO_ATIVAR = "O servidor já foi ativado!";
	public static final String MENSAGEM_SUCESSO_DESATIVAR = "O servidor foi desativado com sucesso!";
	public static final String MENSAGEM_ERRO_DESATIVAR = "O servidor já está desativado!";
	
	public Servidor() throws IOException {
		s = new ServerSocket();
	}
	
	public String desativar() throws IOException {
		s.close();
		s = null;
		
		return MENSAGEM_SUCESSO_DESATIVAR;
	}
	/**
	 * 
	 * @return
	 * @throws ServidorJaAtivadoException
	 */
	public String ativar() {
		
		t = new Thread(this);
		return MENSAGEM_SUCESSO_ATIVAR;
	}

	public void clear() throws IOException {
		t.stop();
		t = null;
		s.close();
		s = null;
	}
	
	public static void reset() {}
	
	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = s.accept();
				new ObjectOutputStream(socket.getOutputStream()).writeObject("123");
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean estaAtivado() {
		if (s != null) {
			return true;
		}
		return false;
	}
}