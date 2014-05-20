package com.codigomestre.cliente.view;

import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main  {
	public static void main(String[] args){
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ClienteGerente cg = new ClienteGerente();
				cg.iniciar();
			}
		});

		NativeInterface.runEventPump();
	}
}