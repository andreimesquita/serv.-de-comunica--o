package com.codigomestre.cliente.ws.test;

/**
 * N�o � necess�rio implementar a interface <i>Serializable</i> quando trabalhamos com serializa��o de objetos para XML
 * com a biblioteca XStream.
 *
 * @author �ndrei
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
