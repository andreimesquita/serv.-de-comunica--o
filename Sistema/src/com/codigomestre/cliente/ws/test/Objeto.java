package com.codigomestre.cliente.ws.test;

/**
 * Não é necessário implementar a interface <i>Serializable</i> quando trabalhamos com serialização de objetos para XML
 * com a biblioteca XStream.
 *
 * @author Ândrei
 */
public class Objeto {
	
	private String nome, email;
	
	public Objeto(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
}
