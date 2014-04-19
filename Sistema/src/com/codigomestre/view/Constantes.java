package com.codigomestre.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public interface Constantes {

	Dimension WINDOW_SIZE_MAX = new Dimension(1920, 1080);
	Dimension WINDOW_SIZE_MIN = new Dimension(1280, 720);

	Dimension BACKGROUND_MINIMUM_SIZE = new Dimension(600, 480);
	Dimension BACKGROUND_PREFERRED_SIZE = new Dimension(600, 48);

	Dimension HOMEDIREITAVIEW_PREFERRED_SIZE = new Dimension(300, 10);
	Dimension HOMEDIREITAVIEW_MINIMUM_SIZE = new Dimension(300, 500);
	Dimension HOMEDIREITAVIEW_MAXIMUM_SIZE = new Dimension(300, 32767);

	Dimension SALASLISTVIEW_PREFERRED_SIZE = new Dimension(280, 2);
	Dimension SALASLISTVIEW_MINIMUM_SIZE = new Dimension(280, 27);
	Dimension SALASLISTVIEW_MAXIMUM_SIZE = new Dimension(280, 32767);

	Dimension BOTAO_SALA_PREFERRED_MINIMUM_MAXIMUM_SIZE = new Dimension(270, 60);

	Dimension USUARIOSLISTAVIEW_MINIMUM_SIZE = new Dimension(50, 100);
	Dimension USUARIOSLISTAVIEW_MAXIMUM_SIZE = new Dimension(50, 32767);

	JLabel LBL_NOT_IMPLEMENTED = new JLabel("Ainda não implementado.");

	Font FONT_NORMAL = new Font(Font.DIALOG, Font.PLAIN, 15);
	Font FONT_BIG = new Font(Font.DIALOG, Font.PLAIN, 20);

}
