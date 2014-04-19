package com.codigomestre.view;

import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.browser.WindowEvent;

public class Janela extends JFrame implements WindowListener {
	private static final String TITULO = "codigomestre - Serviço de Comunicação";

	public Janela() {
		super(Janela.TITULO);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setMinimumSize(Constantes.WINDOW_SIZE_MIN);
		setMaximumSize(Constantes.WINDOW_SIZE_MAX);

		addWindowListener(this);
	}

	public void fechar() {
		int result = JOptionPane.showConfirmDialog(this,
				"Tem certeza que deseja sair?", "Fechar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == 0) {
			this.dispose();
			System.exit(0);
		}
	}

	public void windowActivated(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosed(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(java.awt.event.WindowEvent arg0) {
		fechar();

	}

	public void windowDeactivated(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(java.awt.event.WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
