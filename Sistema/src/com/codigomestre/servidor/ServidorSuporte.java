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
	
	private static final String CODIGO_CADASTRAR = "cu", CODIGO_CADASTRAR_SUCESSO = "cus";
	
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
		f.close();
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream obo = new ObjectOutputStream( cliente.getOutputStream());
			ObjectInputStream obi = new ObjectInputStream(cliente.getInputStream());
			while (true) {
				Properties proRespostas = new Properties();
				
				Properties pro = (Properties) obi.readObject();
				String s = pro.getProperty("codigo");
				switch (s) {
				case CODIGO_CADASTRAR:
					proRespostas.put("codigo", CODIGO_CADASTRAR_SUCESSO);
					obo.writeObject(proRespostas);
					break;

				case "tc":
					proRespostas.put("codigo", "rtc");
					obo.writeObject(proRespostas);
					break;
				}
			}
		} catch (Exception e) {
			
		}
		
	}
	
}
