package com.codigomestre.cliente.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.codigomestre.model.pojo.UsuarioHomeVO;

public class UserView extends JPanel {

	private UsuarioHomeVO usuario;
	private JLabel lbl_nome = new JLabel();
	private JLabel img_usuario = new JLabel();
	private static final String OLA_PADRAO = "  Olá, %!";

	public UserView() {
		setLayout(new BorderLayout());

		setMaximumSize(Constantes.WINDOW_SIZE_MAX);
		setMinimumSize(Constantes.WINDOW_SIZE_MIN);
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		lbl_nome.setFont(Constantes.FONT_BIG);

		add(BorderLayout.WEST, img_usuario);
		add(BorderLayout.CENTER, lbl_nome);
	}

	/**
	 * Apresenta o nome e imagem do usuário na tela.
	 * 
	 * @param usuario
	 */
	public void setUsuario(UsuarioHomeVO usuario) {
		String nomeFinal = OLA_PADRAO.replace("%", usuario.getNome());
		lbl_nome.setText(nomeFinal);
		img_usuario.setIcon(new ImageIcon(usuario.getImg()));
	}
}
