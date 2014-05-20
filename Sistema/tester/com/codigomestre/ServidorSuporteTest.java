package com.codigomestre;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.Servidor;

public class ServidorSuporteTest {

	private static final String[] DADOS_USUARIO_TEST = { "nome", "email",
			"senha" };
	private static final int NOME = 0, EMAIL = 1, SENHA = 2;
	private static final String CODIGO_CADASTRAR = "cu",
			RETORNO_CADASTRAR_SUCESSO = "scu",
			RETORNO_CADASTRAR_FRACASSO = "fcu", CODIGO_LOGAR = "l",
			RETORNO_LOGIN_SUCESSO = "ls", CODIGO_DESLOGAR = "d",
			RETORNO_DESLOGAR = "rd";

	private static Socket s;
	private static Thread socketThread;

	private static ObjectOutputStream objout;
	private static ObjectInputStream objin;

	@Before
	public void setUp() throws Exception {
		if (socketThread == null) {
			socketThread = new Thread(new Servidor());
			socketThread.start();
			s = new Socket("127.0.0.1", Servidor.PORTA);
			objin = new ObjectInputStream((s.getInputStream()));
			objout = new ObjectOutputStream(s.getOutputStream());
		}

		Properties pro = new Properties();
		pro.put("codigo", "reset");
		objout.writeObject(pro);
	}

	@Test
	public void testGetAllClients() throws Exception {
		File fileteste = new File(
				"XmlTest/requisicao_lista_de_clientes.properties");
		Properties pro = new Properties();
		pro.load(new FileInputStream(fileteste));
		objout.reset();
		objout.writeObject(pro);

		Properties p = (Properties) objin.readObject();
		String st = p.getProperty("codigo");
		assertEquals("rtc", st);
	}

	@Test
	public void testSucessoRegistrar() throws Exception {
		Properties pro = new Properties();

		Usuario u = new Usuario(DADOS_USUARIO_TEST[NOME],
				DADOS_USUARIO_TEST[EMAIL], DADOS_USUARIO_TEST[SENHA]);

		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);

		objout.flush();
		objout.writeObject(pro);

		Properties p = (Properties) objin.readObject();

		String st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_SUCESSO, st);
	}
	
	@Test
	public void testFracassoRegistroEmailExistente() throws Exception {
		Properties pro = new Properties();

		Usuario u = new Usuario("01", DADOS_USUARIO_TEST[EMAIL], "0");

		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);

		objout.writeObject(pro);
		
		Properties p = (Properties) objin.readObject();
		
		String st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_SUCESSO, st);
		
		objout.writeObject(pro);
		
		p = (Properties) objin.readObject();

		st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_FRACASSO, st);
		
		st = p.getProperty("mensagem");
		assertNotNull(st);
	}

	@Test
	public void testFracassoRegistroNomeUsuarioExistente() throws Exception {
		Properties pro = new Properties();

		Usuario u = new Usuario(DADOS_USUARIO_TEST[NOME], "d@l", "0");

		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);

		objout.writeObject(pro);
		Properties p = (Properties) objin.readObject();
		String st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_SUCESSO, st);
		objout.writeObject(pro);
		p = (Properties) objin.readObject();
		st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_FRACASSO, st);
		st = p.getProperty("mensagem");
		assertNotNull(st);
	}
	
	@Test
	public void testSucessoLogin() throws Exception {
		Properties pro = new Properties();

		Usuario u = new Usuario(DADOS_USUARIO_TEST[NOME],
				DADOS_USUARIO_TEST[EMAIL], DADOS_USUARIO_TEST[SENHA]);

		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);

		objout.writeObject(pro);

		Properties p = (Properties) objin.readObject();

		String st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_SUCESSO, st);

		pro = new Properties();
		
		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_LOGAR);

		objout.writeObject(pro);

		p = (Properties) objin.readObject();

		st = p.getProperty("codigo");
		assertEquals(RETORNO_LOGIN_SUCESSO, st);
	}
	
	@Test
	public void testSucessoDeslogar() throws Exception {
		Properties pro = new Properties();

		Usuario u = new Usuario(DADOS_USUARIO_TEST[NOME],
				DADOS_USUARIO_TEST[EMAIL], DADOS_USUARIO_TEST[SENHA]);

		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_CADASTRAR);

		objout.flush();
		objout.writeObject(pro);

		Properties p = (Properties) objin.readObject();

		String st = p.getProperty("codigo");
		assertEquals(RETORNO_CADASTRAR_SUCESSO, st);
		
		pro = new Properties();
		
		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_LOGAR);
		
		objout.flush();
		objout.writeObject(pro);
		
		p = (Properties) objin.readObject();

		st = p.getProperty("codigo");
		assertEquals(RETORNO_LOGIN_SUCESSO, st);
		
		pro = new Properties();
		
		pro.put("nu", u.getNomeUsuario());
		pro.put("e", u.getEmail());
		pro.put("s", u.getSenha());
		pro.put("codigo", CODIGO_DESLOGAR);
		
		objout.flush();
		objout.writeObject(pro);
		
		p = (Properties) objin.readObject();

		st = p.getProperty("codigo");
		assertEquals(RETORNO_DESLOGAR, st);
	}
}