package com.codigomestre.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.Servidor;

public class ServidorTest extends TestCase {

	private Servidor servidor;
	private static final String[] DADOS_USUARIO_TEST = { "nome", "email",
			"senha" };
	private static final int NOME = 0, EMAIL = 1, SENHA = 2;
	private static final String CODIGO_CADASTRAR = "cu", CODIGO_CADASTRAR_SUCESSO = "cus";
	
	
	@Override
	protected void setUp() throws Exception {
		servidor = new Servidor();
		servidor.reset();
		super.setUp();
	}

	@Test
	public void testGetAllClients() throws Exception {
		Socket s = new Socket("127.0.0.1", Servidor.PORTA);
		ObjectOutputStream objout = new ObjectOutputStream(s.getOutputStream());
		File fileteste = new File(
				"XmlTest/requisicao_lista_de_clientes.properties");
		Properties pro = new Properties();
		pro.load(new FileInputStream(fileteste));
		objout.writeObject(pro);
		ObjectInputStream objin = new ObjectInputStream((s.getInputStream()));
		Properties p = (Properties) objin.readObject();
		String st = p.getProperty("codigo");
		assertEquals("rtc", st);
		objin.close();
		objout.close();
		s.close();
	}

	@Test
	public void testSucessoRegistrar() throws Exception {
		Socket s = new Socket("127.0.0.1", Servidor.PORTA);
		ObjectOutputStream objout = new ObjectOutputStream(s.getOutputStream());
		Properties pro = new Properties();

		Usuario u = new Usuario(
				DADOS_USUARIO_TEST[NOME],
				DADOS_USUARIO_TEST[EMAIL],
				DADOS_USUARIO_TEST[SENHA]);
		
		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);
		assertEquals(CODIGO_CADASTRAR, pro.getProperty("codigo"));

		objout.writeObject(pro);
		ObjectInputStream objin = new ObjectInputStream((s.getInputStream()));
		Properties p = (Properties) objin.readObject();
		String st = p.getProperty("codigo");
		assertEquals(CODIGO_CADASTRAR_SUCESSO, st);
		objin.close();
		objout.close();
		s.close();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		servidor.desconectar();
	}
}