package com.codigomestre.repositorio;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.pojo.Usuario;
import com.codigomestre.pojo.UsuarioJaEstaNaSalaException;
/**
 *   HU2C01
 * @version 1
 * @author Ândrei
 */
public class SalasTest {
	
	@Before
	public void setUp() {
		Salas.limparERecriarSalas();
	}
	
	@Test
	public void testEntrarNaSala()  throws UsuarioJaEstaNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u);
		
		Sala s = Salas.getSala("Java");
		
		assertTrue(s.estaNaSala(u));
	}
	
	@Test (expected=UsuarioJaEstaNaSalaException.class)
	public void testEntrarNaSalaDuplicata() throws UsuarioJaEstaNaSalaException {
		Usuario u = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u);
		
		Usuario u2 = new Usuario("andrei", "andreirs@outlook.com", "123", "123");
		Salas.entrarNaSala("Java", u2);
	}

}
