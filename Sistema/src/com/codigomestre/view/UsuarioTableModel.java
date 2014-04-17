package com.codigomestre.view;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {

	private List<UsuarioVO> lista;
	private String[] colunas = {"Nome",""};
	
	public UsuarioTableModel() {
		lista = new ArrayList<>();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	public UsuarioVO getRow(int row) {
		int s = row;
		if (row < 0) {
			s = 0;
		} else if (row > lista.size()) {
			s = lista.size();
		}
		return lista.get(s);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Object resultado = null;
		
		switch(coluna) {
		case 0:
			resultado = lista.get(linha).getNome();
			break;
		case 1:
			resultado = lista.get(linha).getIcone();
			break;
		}		
		
		return resultado;
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public void criarDemo() {
		lista.add(new UsuarioVO("Ândrei Mesquita","Imagem/online.png"));
		lista.add(new UsuarioVO("Maurício Carvalho","Imagem/offline.png"));
		lista.add(new UsuarioVO("Bruno Welausen","Imagem/online.png"));

		fireTableRowsInserted(0, 0);
	}
	
	@Override
	public String getColumnName(int arg0) {
		return colunas[arg0];
	}
}
