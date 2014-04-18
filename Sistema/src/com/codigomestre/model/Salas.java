package com.codigomestre.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codigomestre.model.pojo.Sala;
import com.codigomestre.model.pojo.Usuario;

/**
 * HU2C01
 * 
 * @version 1
 * @author Ândrei
 */
public class Salas {

	private static final String[] nomeSalas = {
		"Android","Arduino","C/C++","C#","HTML","Java","JavaScript","JQuery","Python","PHP","Ruby"
	};
	private static Map<String, Sala> salas = new HashMap<>(nomeSalas.length);
	
	public void entrarNaSala(String nomeSala, Usuario u)
			throws UsuarioJaEstaNaSalaException,
			NaoFoiPossivelEntrarNaSalaException {
		Sala s = getSala(nomeSala);
		if (s == null) throw new NaoFoiPossivelEntrarNaSalaException("Não foi possível entrar na sala.");
		s.entrar(u);
	}

	public Sala getSala(String nomeSala) {
		return salas.get(nomeSala);
	}

	public List<Sala> getSalas(String ... nomes) {
		List<Sala> lista = new ArrayList<>();
		
		for (String s : nomes) {
			lista.add(getSala(s));
		}
		
		return lista;
	}
	
	public void limparERecriarSalas() {
		if (salas.size() > 0) {
			for (String nomeSala : nomeSalas) {
				getSala(nomeSala).clear();
			}
			salas.clear();
		}

		for (String nomeSala : nomeSalas) {
			salas.put(nomeSala, new Sala());
		}
	}

	public void SairDaSala(String nomeSala, Usuario u) {
		Sala s = getSala(nomeSala);
		s.remover(u);
	}

	public String[] getNomeSalas() {
		return nomeSalas;
	}

	public List<Sala> getSalaPorUsuario(Usuario u) {
		List<Sala> lista = new ArrayList<>();
		
		for (String nomeSala : nomeSalas) {
			Sala s = salas.get(nomeSala);
			if (s.estaNaSala(u)) {
				lista.add(s);
			}
		}
		
		return lista;
	}

}
