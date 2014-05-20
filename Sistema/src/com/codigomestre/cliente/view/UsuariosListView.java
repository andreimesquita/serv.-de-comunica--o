package com.codigomestre.cliente.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class UsuariosListView extends JScrollPane {

	private JTable lista;
	private UsuarioTableModel model;
	private JFrame janela;

	public UsuariosListView(JFrame janela) {
		super();

		this.janela = janela;

		lista = new JTable();
		lista.setRowSelectionAllowed(true);
		lista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lista.setUpdateSelectionOnSort(false);
		lista.setRequestFocusEnabled(false);
		lista.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lista.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		lista.setShowVerticalLines(true);
		lista.setShowHorizontalLines(true);
		lista.setColumnSelectionAllowed(false);
		lista.setDragEnabled(false);
		lista.setFocusable(false);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		lista.addMouseMotionListener(new MouseMotionListener() {

			private Robot bot;
			private int mask = 0;
			private Toast toast;
			private int selectedRow = -1;

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (toast == null) {
					toast = new Toast(janela);
				}
				if (bot == null) {
					try {
						bot = new Robot();
					} catch (AWTException e) {
						e.printStackTrace();
					}
				}
				if (mask == 0) {
					mask = InputEvent.BUTTON1_MASK;
				}

				bot.mousePress(mask);
				bot.mouseRelease(mask);

				int sr = lista.getSelectedRow();

				if (sr != selectedRow) {
					selectedRow = sr;
					UsuarioVO usuario = model.getRow(lista.getSelectedRow());

					if (toast.isThreadIniciada()) {
						toast.pararThread();
					}

					toast.setMsg("Você clicou em \"" + usuario.getNome() + "\"");
					toast.setDuracao(Toast.RAPIDO);
					toast.iniciarThread();
				}
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
		});

		setMinimumSize(Constantes.USUARIOSLISTAVIEW_MINIMUM_SIZE);
		setMaximumSize(Constantes.USUARIOSLISTAVIEW_MAXIMUM_SIZE);
		// setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		model = new UsuarioTableModel();

		lista.setModel(model);
		// Define que a segunda coluna pode apresentar ImageIcons
		lista.getColumnModel().getColumn(1)
				.setCellRenderer(new UsuarioListaCellRender());
		lista.getColumnModel().getColumn(1).setMaxWidth(20);
		lista.setSelectionBackground(Color.GRAY);

		lista.setFillsViewportHeight(true);

		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		setMinimumSize(getMinimumSize());
		setMaximumSize(getMaximumSize());

		setViewportView(lista);

		model.criarDemo();
	}
}
