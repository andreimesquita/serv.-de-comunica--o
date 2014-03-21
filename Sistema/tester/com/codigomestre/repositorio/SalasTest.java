package com.codigomestre.repositorio;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.pojo.Usuario;
import com.codigomestre.pojo.UsuarioJaEstaNaSalaException;

/**
 * HU2C01
 * 
 * @version 1
 * @author �ndrei
 */
public class SalasTest {

	@Before
	public void setUp() {
		Salas.limparERecriarSalas();
	}

	@Test
	public void testEntrarNaSala() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u);
		Sala s = Salas.getSala("Java");
		assertTrue(s.estaNaSala(u));
	}

	@Test(expected = UsuarioJaEstaNaSalaException.class)
	public void testEntrarNaSalaDuplicata()
			throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u);

		Usuario u2 = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u2);
	}

	@Test(expected = NaoFoiPossivelEntrarNaSalaException.class)
	public void testNaoFoiPossivelEntrarNaSala() throws UsuarioJaEstaNaSalaException,
	NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Inexistente", u);
	}

	@Test
	public void testSairDeUmaSala() throws UsuarioJaEstaNaSalaException, NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u);
		Salas.SairDaSala("Java", u);
	}
	
}