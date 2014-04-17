package com.codigomestre.servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.codigomestre.model.pojo.Sala;

public class ServidorSuporte implements Runnable {
	
	private Socket cliente;
	private Thread t;
	private List<Sala> lista;
	
	public ServidorSuporte(Socket clienteAtual) {
		this.cliente=clienteAtual;
		t=new Thread(this);
		t.start();
	}

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		File file = new File("XmlTest/requisicao_lista_de_clientes.properties");
		//String st = ServidorSuporte.class.getClassLoader().getResource("/XmlTest/requisicao_lista_de_clientes.xml").toString();
		//System.out.println(file.getAbsolutePath());
		FileInputStream  f = new FileInputStream(file.getAbsoluteFile());
		prop.load(f);
		String s = prop.getProperty("codigo");
		System.out.println(s);
		f.close();
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream obo = new ObjectOutputStream( cliente.getOutputStream());
			ObjectInputStream obi = new ObjectInputStream(cliente.getInputStream());
			while (true) {
				System.out.println("MENSAGEM RECEBIDA");
				Properties pro = (Properties) obi.readObject();
				String s = pro.getProperty("codigo");
				System.out.println(s);
				if (s.equals("tc")) {
					Properties pp = new Properties();
					pp.put("codigo", "rtc");
					obo.writeObject(pp);
				}
			}
		} catch (Exception e) {
			
		}
		
	}
	
}
