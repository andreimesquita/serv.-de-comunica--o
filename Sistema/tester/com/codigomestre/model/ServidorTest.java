package com.codigomestre.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Test;

import com.codigomestre.servidor.Servidor;


public class ServidorTest extends TestCase {

	private Servidor servidor = new Servidor();		
	
	@Test
	public void testGetAllClients() throws Exception {
		Socket s = new Socket("127.0.0.1", Servidor.PORTA);
		ObjectOutputStream objout = new ObjectOutputStream (s.getOutputStream()); 
		File fileteste = new File("XmlTest/requisicao_lista_de_clientes.properties");
		Properties pro = new Properties();
		pro.load(new FileInputStream(fileteste));
		objout.writeObject(pro);
		ObjectInputStream objin = new ObjectInputStream( (s.getInputStream()));
		Properties p = (Properties)	objin.readObject();
		String st = p.getProperty("codigo");
		assertEquals("rtc", st);
		System.out.println(st);
		objin.close();
		objout.close();
		s.close();
	}
	
	@AfterClass
	public void afterTestDesconectarServidor() throws IOException {
		servidor.desconectar();
	}
}