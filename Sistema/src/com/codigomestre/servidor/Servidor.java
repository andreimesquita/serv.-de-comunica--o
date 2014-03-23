package com.codigomestre.servidor;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	
	private Thread t;
	private ServerSocket serv;
	
	public Servidor() throws Exception {
		serv = new ServerSocket(5000);
		System.out.println("Socket iniciado!");
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("O servidor está online!");
		while (true) {
			try {
				Socket s = serv.accept();
				new ObjectOutputStream(s.getOutputStream()).writeObject("Hello World, CloudBees!");
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}