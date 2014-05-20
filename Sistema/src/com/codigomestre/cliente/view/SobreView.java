package com.codigomestre.cliente.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SobreView extends JDialog {

	private static final String MSG_SOBRE = "<html><body align=center><b>Serviço de Comunicação - codigomestre</b><br/>"
			+ "Version: 2.0<br/>"
			+ "(c) Copyright 2014.  Todos os direitos reservados.</body></html>";

	public SobreView(JFrame janela) {
		super(janela);
		JLabel lbl = new JLabel(MSG_SOBRE);
		JButton btn = new JButton("OK");
		btn.addActionListener((e) -> {
			SobreView.this.dispose();
			janela.setEnabled(true);
			janela.requestFocus();
		});

		getContentPane().add(BorderLayout.CENTER, lbl);
		getContentPane().add(BorderLayout.SOUTH, btn);

		pack();

		setLocationRelativeTo(janela);
		janela.setEnabled(false);

		setVisible(true);
	}
}
