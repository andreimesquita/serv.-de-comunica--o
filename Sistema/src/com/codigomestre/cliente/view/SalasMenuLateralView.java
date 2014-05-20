package com.codigomestre.cliente.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SalasMenuLateralView extends JScrollPane {

	private JPanel painel;
	private String[] nomeSalas = { "Java", "Android", "Arduino" };
	private JButton[] linhas = new JButton[nomeSalas.length];
	private String[] imagens = {
			"http://png-1.findicons.com/files/icons/2336/wpzoom_social_networking_icon/32/android.png",
			"http://png-1.findicons.com/files/icons/1007/crystal_like/32/java.png",
			"http://i35.servimg.com/u/f35/18/41/94/59/arduin10.png" };

	public SalasMenuLateralView() {

		painel = new JPanel();
		painel.setLayout(new GridLayout(linhas.length, 1));

		iniciarBotoes();

		setViewportView(painel);
	}

	/**
	 * @deprecated Este método serve apenas para fins de teste.
	 */
	private void iniciarBotoes() {
		for (int i = 0; i < linhas.length; i++) {
			linhas[i] = new JButton();
			linhas[i].setFocusable(false);
			linhas[i].setMinimumSize(new Dimension(200, 100));
			linhas[i]
					.setText("<html><img height=\"42\" width=\"42\" src=\""
							+ imagens[i]
							+ "\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"6\">"
							+ nomeSalas[i] + "</font></html>");
			painel.add(linhas[i]);
		}
	}

	public static void main(String[] args) {
		JFrame janela = new JFrame("Teste SalasMenuLateralView");
		janela.setMinimumSize(new Dimension(450, 180));
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SalasMenuLateralView menuLateral = new SalasMenuLateralView();
		janela.setContentPane(menuLateral);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
}