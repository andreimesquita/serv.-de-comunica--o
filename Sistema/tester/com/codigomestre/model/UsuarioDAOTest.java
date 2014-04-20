package com.codigomestre.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.dao.UsuarioDAO;
import com.codigomestre.servidor.exception.LoginEmailIncorretoException;
import com.codigomestre.servidor.exception.LoginSenhaIncorretaException;

public class UsuarioDAOTest {

	private static UsuarioDAO dao;
	
	@Before
	public void setUp() throws Exception {
		if (dao == null) {
			dao = new UsuarioDAO();
		}
		dao.reset();
	}

	@Test
	public void testSucessoCadastro() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);

		boolean igual = false;
		for (Usuario u : dao.getAll()) {
			if (u.getEmail().equals(usuario.getEmail())) {
				igual = true;
				break;
			}
		}
		assertTrue(igual);
	}

	@Test(expected = ErroCadastroEmailDuplicadoException.class)
	public void testFracassoCadastroEmailDuplicado() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		Usuario usuarioCopy = new Usuario("nome2", "email", "senha");
		dao.cadastrar(usuarioCopy);
	}

	@Test(expected = ErroCadastroNomeDuplicadoException.class)
	public void testFracassoCadastroNomeDuplicado() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		Usuario usuarioCopy = new Usuario("nome", "email2", "senha");
		dao.cadastrar(usuarioCopy);
	}

	@Test
	public void testSucessoLogar() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		assertFalse(dao.isOnline(usuario));
		dao.logar(usuario);
		assertTrue(dao.isOnline(usuario));
	}

	@Test (expected = LoginEmailIncorretoException.class)
	public void testFracassoLogarEmailIncorreto() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		Usuario usuarioEmailIncorreto = new Usuario("nome", "email2", "senha");
		dao.logar(usuarioEmailIncorreto);
	}

	@Test (expected = LoginSenhaIncorretaException.class)
	public void testFracassoLogarSenhaIncorreta() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		Usuario usuarioSenhaIncorreta = new Usuario("nome", "email", "senha2");
		dao.logar(usuarioSenhaIncorreta);
	}
	
	@Test
	public void testDeslogar() throws Exception {
		Usuario usuario = new Usuario("nome", "email", "senha");
		dao.cadastrar(usuario);
		dao.logar(usuario);
		assertTrue(dao.isOnline(usuario));
		dao.deslogar(usuario);
		assertFalse(dao.isOnline(usuario));
	}
	
	@Test
	public void testGetAll() throws Exception {
		Usuario[] us = {new Usuario("1", "2", "3"),new Usuario("2", "3", "4"),new Usuario("3", "4", "5")};
		for (Usuario u : us) {
			dao.cadastrar(u);
		}
		
		assertEquals(us.length, dao.getAll().size());
	}
	
}