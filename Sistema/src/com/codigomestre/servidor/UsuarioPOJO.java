package com.codigomestre.servidor;

public class UsuarioPOJO {

	private String nomeUsuario, email;

	public UsuarioPOJO(String nomeUsuario, String email) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
