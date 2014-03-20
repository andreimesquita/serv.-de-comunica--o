package com.codigomestre.pojo;

import org.junit.Before;
import org.junit.Test;

public class CadastroUsuarioTest {
	@Before 
	public void setUp() {
		CadastroUsuario.reset();
	}
	@Test
	public void testeCriacaoDeUsuarioComSucesso() throws ErroDuranteCadastroException {
		CadastroUsuario.cadastrar("AndreiRS","andreirs@outlook.com","123","123");
	}
	
	@Test (expected=ErroDuranteCadastroException.class)
	public void testeCriacaoDeUsuarioDuplicata() throws ErroDuranteCadastroException {
		CadastroUsuario.cadastrar("AndreiRS","andreirs@outlook.com","123","123");
		CadastroUsuario.cadastrar("AndreiRS","andreirs@outlook.com","123","123");
	}
}