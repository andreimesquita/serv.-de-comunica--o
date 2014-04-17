package com.codigomestre.model.pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.codigomestre.model.UsuarioJaEstaNaSalaException;
/**
 *   HU2C01
 * @version 1
 * @author Ândrei
 */
public class Sala {
	
	private List<Usuario> lista = new ArrayList<>();
	private static String ultimaMensagem;
	
	public boolean estaNaSala(Usuario u) {
		for (Usuario us : lista) {
			if (us.getNomeUsuario().equals(u.getNomeUsuario())) return true;
		}
		return false;
	}
	
	public void entrar(Usuario u)  throws UsuarioJaEstaNaSalaException {
		for (Usuario usuario : lista) {
			if (usuario.getNomeUsuario().equals(u.getNomeUsuario())) throw new UsuarioJaEstaNaSalaException("O usuário já está na sala!");
		}
		lista.add(u);
	}

	public void clear() {
		lista.clear();
	}

	public void remover(Usuario u) {
		for (Usuario usuario : lista) {
			if (usuario.getNomeUsuario().equals(u.getNomeUsuario())) {
				lista.remove(usuario);
				break;
			}
		}
	}

	public static void escrever(String nome, String mensagem) {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		Sala.ultimaMensagem = nome + " [" + data.getHours() + "h:" + data.getMinutes() + "m]:" + mensagem;
	}

	public static String getUltimaMensagem() {
		return ultimaMensagem;
	}
	
}
