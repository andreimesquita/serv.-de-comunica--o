package com.codigomestre.servidor.view;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ServidorView {

	SystemTray st;

	public ServidorView() {
		File arq = new File("Imagem/kraicon.jpg");
		BufferedImage jpg = null;

		try {
			jpg = ImageIO.read(arq);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		TrayIcon icon = new TrayIcon(jpg.getScaledInstance(32, 32, 32));
		MenuItem item = new MenuItem("ASP");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ALo!");
			}
		});
		PopupMenu menu = new PopupMenu("Alo!");
		menu.add(item);
		icon.setPopupMenu(menu);
		// st.add(icon);
	}
}