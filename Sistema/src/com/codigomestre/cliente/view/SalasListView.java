package com.codigomestre.cliente.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.codigomestre.servidor.ServidorSuporte;

public class SalasListView extends JScrollPane {

	private JPanel painel;

	private JButton[] linhas = new JButton[nomeSalas.length];

	private SalasAbasView salasabasview = SalasAbasView.getInstance();

	private static ObjectOutputStream ous;
	
	private static final String[] nomeSalas = { "Android", "Arduino", "C/C++",
			"C#", "HTML", "Java", "JavaScript", "JQuery", "Python", "PHP",
			"Ruby" };
//	private static final String[] imagens = {
//			"http://png-1.findicons.com/files/icons/1007/crystal_like/32/java.png",
//			"http://i35.servimg.com/u/f35/18/41/94/59/arduin10.png",
//			"http://i19.servimg.com/u/f19/13/69/73/89/c10.jpg",
//			"http://i89.servimg.com/u/f89/13/69/73/89/csharp10.jpg",
//			"http://i35.servimg.com/u/f35/18/41/94/59/html10.jpg",
//			"http://png-1.findicons.com/files/icons/1007/crystal_like/32/java.png",
//			"http://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png",
//			"http://brand.jquery.org/resources/jquery-mark-light.gif",
//			"http://i89.servimg.com/u/f89/13/69/73/89/python10.png",
//			"http://i89.servimg.com/u/f89/13/69/73/89/php10.png",
//			"http://i19.servimg.com/u/f19/13/69/73/89/ruby-l11.jpg", };

	public SalasListView(ObjectOutputStream outputStream) throws IOException {
		super();

		ous = outputStream;
		
		painel = new JPanel();
		painel.setLayout(new GridLayout(linhas.length, 1));
		initBotoes();

		setPreferredSize(Constantes.SALASLISTVIEW_PREFERRED_SIZE);
		setMinimumSize(Constantes.SALASLISTVIEW_MINIMUM_SIZE);
		setMaximumSize(Constantes.SALASLISTVIEW_MAXIMUM_SIZE);

		JScrollBar bar = getVerticalScrollBar();
		bar.setUnitIncrement(50);

		setViewportView(painel);
	}

	int x = 0;
	String[] nomes = { "Android", "Java", "C" };

	private void initBotoes() {
		for (int i = 0; i < linhas.length; i++) {
			linhas[i] = new JButton();
			linhas[i].addActionListener(new AbriAbaAction(i));
			linhas[i].setHorizontalTextPosition(SwingConstants.CENTER);
			linhas[i].setVerticalTextPosition(SwingConstants.CENTER);
			linhas[i]
					.setMaximumSize(Constantes.BOTAO_SALA_PREFERRED_MINIMUM_MAXIMUM_SIZE);
			linhas[i]
					.setMinimumSize(Constantes.BOTAO_SALA_PREFERRED_MINIMUM_MAXIMUM_SIZE);
			linhas[i]
					.setPreferredSize(Constantes.BOTAO_SALA_PREFERRED_MINIMUM_MAXIMUM_SIZE);
			linhas[i].setFocusable(false);
			linhas[i]
					.setText("<html><font size=\"6\">" //<img height=\"42\" width=\"42\" src=\""+ imagens[i]+ "\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							+ nomeSalas[i] + "</font></html>");
			painel.add(linhas[i]);
		}
	}

	private class AbriAbaAction implements ActionListener {
		private int ref;
		
		protected AbriAbaAction(int var) {
			super();
			this.ref = var;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!salasabasview.isNaSala(nomeSalas[ref])) {
				Properties p = new Properties();
				p.put("codigo", ServidorSuporte.CODIGO_ENTRAR_SALA);
				p.put("ns", nomeSalas[ref]);
				try {
					ous.writeObject(p);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "A aplicação não conseguiu se conectar com o servidor.");
				}
			}
		}
	}

}