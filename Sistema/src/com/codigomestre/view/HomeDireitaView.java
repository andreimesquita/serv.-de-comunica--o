package com.codigomestre.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomeDireitaView extends JPanel {

	private UsuariosListView usuariosView;
	private SalasListView salasView;
	
	public HomeDireitaView(Janela janela) {
		setLayout(new GridLayout(2, 1));

		setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		
		setPreferredSize(Constantes.HOMEDIREITAVIEW_PREFERRED_SIZE);
		setMinimumSize(Constantes.HOMEDIREITAVIEW_MINIMUM_SIZE);
		setMaximumSize(Constantes.HOMEDIREITAVIEW_MAXIMUM_SIZE);

		usuariosView = new UsuariosListView(janela);
		salasView = new SalasListView();
		
		add(usuariosView);
		add(salasView);
	}
}
