package com.codigomestre.repositorio;

import java.util.HashMap;
import java.util.Map;

import com.codigomestre.pojo.Usuario;
import com.codigomestre.pojo.UsuarioJaEstaNaSalaException;
/**
 *   HU2C01
 * @version 1
 * @author Ândrei
 */
public class Salas {

	private static Map<String, Sala> salas = new HashMap<>();
	
	public static void entrarNaSala(String nomeSala, Usuario u) throws UsuarioJaEstaNaSalaException {
		salas.get(nomeSala).entrar(u);
	}
	
	public static Sala getSala(String nomeSala) {
		return salas.get(nomeSala);
	}

	public static void limparERecriarSalas() {
		if (salas != null) {
			for (int i = 0; i < salas.size(); i++) {
				salas.get(i).clear();
			}
			salas.clear();
		}
		
		salas.put("Java", new Sala());
		salas.put("Android", new Sala());
	}
	
}
