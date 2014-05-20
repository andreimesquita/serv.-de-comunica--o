package com.codigomestre.cliente.view;

import javax.swing.ImageIcon;

public class UsuarioVO {
	private String nome;
	private ImageIcon icone;

	public UsuarioVO(String nome, String icone) {
		super();
		this.nome = nome;
		this.icone = new ImageIcon(icone);
	}

	public String getNome() {
		return nome;
	}

	public ImageIcon getIcone() {
		return icone;
	}

	public void setOnline(boolean b) {
		if (b) {
			// icone = ONLINE
		} else {
			// icone = OFFLINE
		}
	}
}
