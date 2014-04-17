package com.codigomestre.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SalaChatView extends JPanel {
	
	private JTextField txfEntrada;
	private JButton btnEnviar;
	private String nomeSala;
	
	public SalaChatView(String nomeSala) {
		this.nomeSala = nomeSala;
		
		txfEntrada = new JTextField();
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener((e) -> {
			if (!txfEntrada.getText().equals("")) {
				// Envia a mensagem para o servidor compartilhar com os demais usuários na sala
			}
		});
		btnEnviar.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					// Envia a mensagem para o servidor compartilhar com os demais usuários na sala
				}
			}			
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
	}
	
	public String getNomeSala() {
		return nomeSala;
	}
}
