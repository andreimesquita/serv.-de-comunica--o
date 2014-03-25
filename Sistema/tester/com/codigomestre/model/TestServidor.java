package com.codigomestre.model;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.servidor.Servidor;

public class TestServidor extends TestCase {
	
	private Servidor servidor;
	
	@Before
	public void setUp() throws Exception {
		servidor = new Servidor();
	}
	
	@Test
	public void testAtivar() {
		String resultado = servidor.ativar();
		assertEquals(Servidor.MENSAGEM_SUCESSO_ATIVAR, resultado);
		assertTrue(servidor.estaAtivado());
	}
	
		
	@Test
	public void testDesativar() throws IOException {
		String resultado = servidor.desativar();
		assertEquals(Servidor.MENSAGEM_SUCESSO_DESATIVAR, resultado);
		assertFalse(servidor.estaAtivado());
	}
	
		
	@Override
	protected void tearDown() throws Exception {
		if (servidor.estaAtivado()) {
			servidor.desativar();
		}
	}
	
}
