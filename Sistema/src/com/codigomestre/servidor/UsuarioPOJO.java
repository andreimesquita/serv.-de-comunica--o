package com.codigomestre.servidor;

public class UsuarioPOJO {

	private String nomeUsuario, email, senha;

	public UsuarioPOJO(String nomeUsuario, String email, String senha) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
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
