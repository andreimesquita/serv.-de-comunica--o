package com.codigomestre.model.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Hist�ria de usu�rio sendo implementada HU1C01
 * 
 * @version 1
 * @author Maur�cio / �ndrei
 */
public class Usuario {
	private String nomeUsuario, email, senha, confimacaoSenha;

	public Usuario(String nomeUsuario, String email, String senha) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	/**
	 * Este m�todo retorna as informa��es do usu�rio no formato
	 * Map<String,String> pronto para ser adicionado ao arquivo Properties.
	 * 
	 * @return HashMap contendo as informa��es do usu�rio.
	 */
	public Map<String, String> toHashMap() {
		Map<String, String> map = new HashMap<>();
		map.put("nu", getNomeUsuario());
		map.put("e", getEmail());
		map.put("s", getConfimacaoSenha());
		return map;
	}

}
