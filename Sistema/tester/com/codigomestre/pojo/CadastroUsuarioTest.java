package com.codigomestre.pojo;

import org.junit.Before;
import org.junit.Test;
/**
 *     História de usuário sendo implementada HU1C01
 * @version 1
 * @author Maurício
 */
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