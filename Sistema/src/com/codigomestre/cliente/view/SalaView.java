package com.codigomestre.cliente.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SalaView extends JPanel {

	private static final long serialVersionUID = -9196736035004387105L;
	private JButton btn_sair, btn_enviar;
	private JEditorPane editor;
	private JTextField field_mensagem;

	private Font f = new Font(Font.DIALOG, Font.PLAIN, 20);

	private SalasAbasView sav;
	
	public void escrever(String mensagem) {
		mensagem = (editor.getText().equals("") ? "" : "\n" ) + mensagem;
		editor.setText(mensagem);
	}

	public SalaView(String nome) {
		super();
		setLayout(new BorderLayout());
		JPanel sup_esq = new JPanel();
		sup_esq.setLayout(new BorderLayout());
		sav = SalasAbasView.getInstance();
		btn_sair = new JButton("Sair da sala");
		btn_sair.addActionListener((e) -> {
			sav.removerSala(nome);
		});
		sup_esq.add(BorderLayout.EAST, btn_sair);

		add(BorderLayout.NORTH, sup_esq);

		JPanel chat = new JPanel();
		chat.setLayout(new BorderLayout());
		editor = new JEditorPane();
		editor.setText("");
		editor.setFont(f);
		editor.setEditable(false);
		editor.setBackground(Color.gray);
		editor.setForeground(Color.BLACK);

		JScrollPane editor_scroll = new JScrollPane(editor);
		editor_scroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		editor_scroll.getHorizontalScrollBar().setOpaque(false);
		editor_scroll.getHorizontalScrollBar().setVisibleAmount(1);
		chat.add(BorderLayout.CENTER, editor_scroll);

		JPanel inf = new JPanel();
		inf.setLayout(new BorderLayout());
		btn_enviar = new JButton("Enviar");
		btn_enviar.setFont(f);
		btn_enviar.setEnabled(false);
		inf.add(BorderLayout.EAST, btn_enviar);
		field_mensagem = new JTextField();
		field_mensagem.setFont(f);
		field_mensagem.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btn_enviar.doClick();
				}
			}

		});
		inf.add(BorderLayout.CENTER, field_mensagem);
		chat.add(BorderLayout.SOUTH, inf);
		add(BorderLayout.CENTER, chat);

		field_mensagem.requestFocus();
	}

}
