package com.codigomestre.model;

import org.junit.Test;

import com.codigomestre.servidor.dao.Conexao;

public class ConexaoTest {
	
	private static Conexao conexao = Conexao.getInstance();
	
	@Test
	public void testConectarComBanco() throws Exception {
		conexao.conectar();
	}
	
}
