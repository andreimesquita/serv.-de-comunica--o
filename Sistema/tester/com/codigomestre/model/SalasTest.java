package com.codigomestre.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.pojo.Sala;
import com.codigomestre.model.pojo.Usuario;

/**
 * Version 1 HU2C01 HU2C02 HU2C03 Version 2 HU6C01 HU6C02
 * 
 * @version 2
 * @author Ândrei
 */
public class SalasTest {

	private Salas salas = new Salas();

	@Before
	public void setUp() {
		salas.limparERecriarSalas();
	}

	@Test
	public void testEntrarNaSala() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");
		salas.entrarNaSala("Java", u);
		Sala s = salas.getSala("Java");
		assertTrue(s.estaNaSala(u));
	}

	@Test(expected = UsuarioJaEstaNaSalaException.class)
	public void testEntrarNaSalaDuplicata()
			throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");
		salas.entrarNaSala("Java", u);
		salas.entrarNaSala("Java", u);
	}

	@Test(expected = NaoFoiPossivelEntrarNaSalaException.class)
	public void testSalaInexistente() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");
		salas.entrarNaSala("Inexistente", u);
	}

	@Test
	public void testSairDeUmaSala() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");
		salas.entrarNaSala("Java", u);
		salas.SairDaSala("Java", u);
	}

	@Test
	public void testEntrarEmVariasSalas() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");

		String[] nomeSalas = salas.getNomeSalas();

		for (String nomeSala : nomeSalas) {
			salas.entrarNaSala(nomeSala, u);
		}

		List<Sala> salasUsuario = salas.getSalaPorUsuario(u);

		assertEquals(nomeSalas.length, salasUsuario.size());
	}

	@Test
	public void testSairDeVariasSalas() throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123");
		// a
		String[] nomeSalas = salas.getNomeSalas();

		for (String nomeSala : nomeSalas) {
			salas.entrarNaSala(nomeSala, u);
		}

		List<Sala> salasUsuarioOld = salas.getSalaPorUsuario(u);

		salas.SairDaSala(nomeSalas[2], u);

		List<Sala> salasUsuarioNew = salas.getSalaPorUsuario(u);

		assertEquals(salasUsuarioOld.size() - 1, salasUsuarioNew.size());
	}
}