package com.codigomestre.model.pojo;
/**
 *     Hist�ria de usu�rio sendo implementada HU1C01
 * @version 1
 * @author Maur�cio / �ndrei
 */
public class Usuario {
	private String nomeUsuario, email, senha, confimacaoSenha;
	
	public Usuario(String nomeUsuario, String email, String senha,
			String confimacaoSenha) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.confimacaoSenha = confimacaoSenha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfimacaoSenha() {
		return confimacaoSenha;
	}

	public void setConfimacaoSenha(String confimacaoSenha) {
		this.confimacaoSenha = confimacaoSenha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
