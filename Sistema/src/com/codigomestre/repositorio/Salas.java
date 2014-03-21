package com.codigomestre.repositorio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.codigomestre.pojo.Usuario;
import com.codigomestre.pojo.UsuarioJaEstaNaSalaException;

/**
 * HU2C01
 * 
 * @version 1
 * @author Ândrei
 */
public class Salas {

	private static Map<String, Sala> salas = new HashMap<>();

	public static void entrarNaSala(String nomeSala, Usuario u)
			throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Sala s = getSala(nomeSala);
		if (s == null) throw new NaoFoiPossivelEntrarNaSalaException("Não foi possível entrar na sala.");
		s.entrar(u);
	}

	public static Sala getSala(String nomeSala) {
		return salas.get(nomeSala);
	}

	public static void limparERecriarSalas() {
		if (salas.size() > 0) {
			Set<String> set = salas.keySet();
			for (String nomeSala : set) {
				getSala(nomeSala).clear();
			}
			salas.clear();
		}

		salas.put("Java", new Sala());
		salas.put("Android", new Sala());
	}

	public static void SairDaSala(String nomeSala, Usuario u) {
		Sala s = getSala(nomeSala);
		s.remover(u);
	}

}
