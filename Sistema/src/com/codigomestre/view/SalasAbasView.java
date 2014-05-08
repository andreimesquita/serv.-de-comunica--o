package com.codigomestre.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class SalasAbasView extends JTabbedPane {

	private List<SalaABA> abas = new ArrayList<>();

	private static SalasAbasView salasAbasView;

	public static SalasAbasView getInstance() {
		if (salasAbasView == null) {
			salasAbasView = new SalasAbasView();
		}
		return salasAbasView;
	}

	private SalasAbasView() {
		super();

		setFont(Constantes.FONT_BIG);

		setFocusable(false);

		CriarDemo();
	}

	public void removerSala(String string) {
		for (int x = 0; x < abas.size(); x++) {
			String nome = abas.get(x).getNome();
			if (nome.equals(string)) {
				remove(x);
				abas.remove(x);
				break;
			}
		}
	}

	public void CriarDemo() {
		JWebBrowser browser = new JWebBrowser(
				JWebBrowser.destroyOnFinalization());
		browser.navigate("http://codigomestre.forumbrasil.net/");
		browser.setMenuBarVisible(false);
		adicionarSala(browser, "Home");
	}

	public void adicionarSala(JPanel sala, String nome) {
		addTab(nome, null, sala, "Tooltip");
		abas.add(new SalaABA(nome));
		// setTabComponentAt(abas.size() - 1, new SalaChatView(nome));
		// setSelectedComponent(getComponent(abas.size() - 1));
	}

	public boolean isNaSala(String string) {
		for (int x = 0; x < abas.size(); x++) {
			String nome = abas.get(x).getNome();
			if (nome.equals(string)) {
				setSelectedComponent(getComponent(x));
				getComponentAt(x).requestFocus();
				return true;
			}
		}
		return false;
	}

	private class SalaABA {
		protected String nome;

		public SalaABA(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
	}
}
