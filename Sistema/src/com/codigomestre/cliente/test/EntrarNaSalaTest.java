package com.codigomestre.cliente.test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import com.codigomestre.servidor.Servidor;
import com.codigomestre.servidor.ServidorSuporte;

public class EntrarNaSalaTest {

	private static Socket soc;

	public static void main(String[] args) throws Exception {
		Servidor serv = new Servidor();
		serv.start();
		soc = new Socket("localhost", Servidor.PORTA);
		
		ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
		
		Properties p = new Properties();
		
		// LOGIN
		
		p.put("codigo", ServidorSuporte.CODIGO_LOGAR);
		p.put("nu", "Adriane Galisteu");
		p.put("e", "adriane@gmail.com");
		p.put("s", "123");
		
		out.writeObject(p);
		
		p = (Properties) in.readObject();

		p.clear();
		p = new Properties();
		p.put("codigo", ServidorSuporte.CODIGO_ENTRAR_SALA);
		p.put("ns", "Java");
		
		out.writeObject(p);
		
		p = (Properties) in.readObject(); // O retorno foi depreciado
		p = (Properties) in.readObject();
		
		String str = p.getProperty("m");
		System.out.println(str);

		out.close();
		serv.desconectar();
	}

}
