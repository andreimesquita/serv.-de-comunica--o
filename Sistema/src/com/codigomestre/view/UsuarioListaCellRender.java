package com.codigomestre.view;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class UsuarioListaCellRender extends DefaultTableCellRenderer {
	@Override
	protected void setValue(Object value) {
		if (value instanceof ImageIcon) {
			if (value != null) {
				setIcon((ImageIcon) value);
			} else {
				setText("");
				setIcon(null);
			}
		} else {
			super.setValue(value);
		}
	}
}
