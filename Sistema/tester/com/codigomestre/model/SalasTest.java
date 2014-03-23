package com.codigomestre.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.pojo.Sala;
import com.codigomestre.model.pojo.Usuario;

/**
 * Version 1
 * 		HU2C01
 *      HU2C02
 *      HU2C03
 * Version 2
 * 		HU6C01
 * 		HU6C02
 * @version 2
 * @author Ândrei
 */
public class SalasTest {

	private Salas salas;
	
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
		Salas.entrarNaSala("Java", u);
	}

	@Test(expected = NaoFoiPossivelEntrarNaSalaException.class)
	public void testSalaInexistente() throws UsuarioJaEstaNaSalaException,
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
	
	@Test
	public void testEntrarEmVariasSalas() throws UsuarioJaEstaNaSalaException, NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");	
		
		String[] nomeSalas = Salas.getNomeSalas();
		
		for (String nomeSala : nomeSalas) {
			Salas.entrarNaSala(nomeSala, u);
		}
		
		List<Sala> salasUsuario = Salas.getSalaPorUsuario(u);
		
		assertEquals(nomeSalas.length,salasUsuario.size());
	}
	
	@Test
	public void testSairDeVariasSalas() throws UsuarioJaEstaNaSalaException, NaoFoiPossivelEntrarNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");	
		
		String[] nomeSalas = Salas.getNomeSalas();
		
		for (String nomeSala : nomeSalas) {
			Salas.entrarNaSala(nomeSala, u);
		}
		
		List<Sala> salasUsuarioOld = Salas.getSalaPorUsuario(u);
		
		Salas.SairDaSala(nomeSalas[2], u);
		
		List<Sala> salasUsuarioNew = Salas.getSalaPorUsuario(u);
		
		assertEquals(salasUsuarioOld.size() - 1, salasUsuarioNew.size());
	}
}