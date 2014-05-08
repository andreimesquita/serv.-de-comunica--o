package com.codigomestre.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.codigomestre.model.pojo.UsuarioHomeVO;

public class ClienteGerente {

	private Janela janela;
	private HomeDireitaView homeDireitaView;
	private SalasAbasView salasAbasView;
	private UserView userView;
	private JPanel homeView, background, principal;

	private JRadioButtonMenuItem btnMenuEditarLookAndFeelAcryl,
			btnMenuEditarLookAndFeelAero, btnMenuEditarLookAndFeelBernstein,
			btnMenuEditarLookAndFeelHiFi, btnMenuEditarLookAndFeelMcWin,
			btnMenuEditarLookAndFeelSmart;

	public void reiniciarGui() {
		try {
			javax.swing.UIManager.setLookAndFeel(Salvar.get("lookandfeel"));
		} catch (Exception e3) {
			JOptionPane
					.showConfirmDialog(
							null,
							"O LookAndFeel selecionado não foi encontrado "
									+ "ou o arquivo \'definicao.xml\' não está presente na pasta!");
			try {
				UIManager.setLookAndFeel(UIManager.getLookAndFeel());
			} catch (Exception es) {
				es.printStackTrace();
			}
		}
		SwingUtilities.updateComponentTreeUI(janela);
		limparSelecaoLAF();
		// janela.dispose();
		// janela = null;
		// homeDireitaView = null;
		// salasAbasView = null;
		// userView = null;
		// background = null;
		// homeView = null;
		// iniciar();
	}

	private void limparSelecaoLAF() {

		switch (Salvar.get("lookandfeel")) {
		case ("com.jtattoo.plaf.acryl.AcrylLookAndFeel"):
			btnMenuEditarLookAndFeelAcryl.setSelected(true);
			btnMenuEditarLookAndFeelAero.setSelected(false);
			btnMenuEditarLookAndFeelBernstein.setSelected(false);
			btnMenuEditarLookAndFeelHiFi.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(false);
			break;
		case ("com.jtattoo.plaf.aero.AeroLookAndFeel"):
			btnMenuEditarLookAndFeelAcryl.setSelected(false);
			btnMenuEditarLookAndFeelBernstein.setSelected(false);
			btnMenuEditarLookAndFeelHiFi.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(false);
			btnMenuEditarLookAndFeelAero.setSelected(true);
			break;
		case ("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"):
			btnMenuEditarLookAndFeelBernstein.setSelected(true);
			btnMenuEditarLookAndFeelAcryl.setSelected(false);
			btnMenuEditarLookAndFeelHiFi.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(false);
			btnMenuEditarLookAndFeelAero.setSelected(false);
			break;
		case ("com.jtattoo.plaf.hifi.HiFiLookAndFeel"):
			btnMenuEditarLookAndFeelHiFi.setSelected(true);
			btnMenuEditarLookAndFeelBernstein.setSelected(false);
			btnMenuEditarLookAndFeelAcryl.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(false);
			btnMenuEditarLookAndFeelAero.setSelected(false);
			break;
		case ("com.jtattoo.plaf.mcwin.McWinLookAndFeel"):
			btnMenuEditarLookAndFeelHiFi.setSelected(false);
			btnMenuEditarLookAndFeelBernstein.setSelected(false);
			btnMenuEditarLookAndFeelAcryl.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(false);
			btnMenuEditarLookAndFeelAero.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(true);
			break;
		case ("com.jtattoo.plaf.smart.SmartLookAndFeel"):
			btnMenuEditarLookAndFeelHiFi.setSelected(false);
			btnMenuEditarLookAndFeelBernstein.setSelected(false);
			btnMenuEditarLookAndFeelAcryl.setSelected(false);
			btnMenuEditarLookAndFeelMcWin.setSelected(false);
			btnMenuEditarLookAndFeelAero.setSelected(false);
			btnMenuEditarLookAndFeelSmart.setSelected(true);
			break;
		}
	}

	private void iniciar() {

		try {
		javax.swing.UIManager.setLookAndFeel(Salvar.get("lookandfeel"));
		} catch (Exception e3) {
			JOptionPane
					.showConfirmDialog(
							null,
							"O LookAndFeel selecionado não foi encontrado "
									+ "ou o arquivo \'definicao.xml\' não está presente na pasta!");
			try {
				UIManager.setLookAndFeel(UIManager.getLookAndFeel());
			} catch (Exception es) {
				es.printStackTrace();
			}
		}

		janela = new Janela();
		janela.setIconImage(new ImageIcon("Imagem/icone.jpg").getImage());

		principal = new JPanel();

		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setBorder(BorderFactory
				.createTitledBorder("Native Web Browser component"));
		// JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4,
		// 4));
		// JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar",
		// webBrowser.isMenuBarVisible());
		// menuBarCheckBox.addItemListener(new ItemListener() {
		// public void itemStateChanged(ItemEvent e) {
		// webBrowser.setMenuBarVisible(e.getStateChange() ==
		// ItemEvent.SELECTED);
		// }
		// });
		// buttonPanel.add(menuBarCheckBox);

		// principalWeb.add(buttonPanel, BorderLayout.SOUTH);

		background = new JPanel();
		homeView = new JPanel();
		salasAbasView = SalasAbasView.getInstance();
		homeDireitaView = new HomeDireitaView(janela);
		userView = new UserView();

		principal.setLayout(new BorderLayout());
		background.setPreferredSize(Constantes.BACKGROUND_PREFERRED_SIZE);
		background.setMinimumSize(Constantes.BACKGROUND_MINIMUM_SIZE);
		background.setLayout(new BorderLayout());
		homeView.setLayout(new BorderLayout());
		homeView.add(BorderLayout.CENTER, salasAbasView);
		homeView.add(BorderLayout.EAST, homeDireitaView);
		background.add(BorderLayout.NORTH, userView);
		background.add(BorderLayout.CENTER, homeView);

		principal.add(BorderLayout.CENTER, background);

		try {
			userView.setUsuario(new UsuarioHomeVO("Ândrei",
					"Imagem/fotoTest.jpg"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		JMenuBar barraMenu = new JMenuBar();
		JMenu menuAjuda = new JMenu("Ajuda");
		menuAjuda.setFont(Constantes.FONT_NORMAL);
		JMenuItem itemMenuAjuda = new JMenuItem("Sobre...");
		itemMenuAjuda.setFont(Constantes.FONT_NORMAL);
		itemMenuAjuda.addActionListener((e) -> {
			new SobreView(janela);
		});
		menuAjuda.add(itemMenuAjuda);

		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setFont(Constantes.FONT_NORMAL);
		JMenuItem menuArquivoItemFechar = new JMenuItem("Fechar...");
		menuArquivoItemFechar.setFont(Constantes.FONT_NORMAL);
		menuArquivoItemFechar.addActionListener((e) -> {
			janela.fechar();
		});
		menuArquivo.add(menuArquivoItemFechar);

		JMenu menuEditar = new JMenu("Editar");
		menuEditar.setFont(Constantes.FONT_NORMAL);
		JMenu menuEditarLookAndFeel = new JMenu("Look and Feel");
		menuEditarLookAndFeel.setFont(Constantes.FONT_NORMAL);

		btnMenuEditarLookAndFeelAcryl = new JRadioButtonMenuItem("Acryl");
		btnMenuEditarLookAndFeelAcryl.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelAcryl.addActionListener(new MeuAction(
				"com.jtattoo.plaf.acryl.AcrylLookAndFeel"));

		btnMenuEditarLookAndFeelAero = new JRadioButtonMenuItem("Aero");
		btnMenuEditarLookAndFeelAero.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelAero.addActionListener(new MeuAction(
				"com.jtattoo.plaf.aero.AeroLookAndFeel"));

		btnMenuEditarLookAndFeelBernstein = new JRadioButtonMenuItem(
				"Bernstein");
		btnMenuEditarLookAndFeelBernstein.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelBernstein.addActionListener(new MeuAction(
				"com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"));

		btnMenuEditarLookAndFeelHiFi = new JRadioButtonMenuItem("HiFi");
		btnMenuEditarLookAndFeelHiFi.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelHiFi.addActionListener(new MeuAction(
				"com.jtattoo.plaf.hifi.HiFiLookAndFeel"));

		btnMenuEditarLookAndFeelMcWin = new JRadioButtonMenuItem("McWin");
		btnMenuEditarLookAndFeelMcWin.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelMcWin.addActionListener(new MeuAction(
				"com.jtattoo.plaf.mcwin.McWinLookAndFeel"));

		btnMenuEditarLookAndFeelSmart = new JRadioButtonMenuItem("Smart");
		btnMenuEditarLookAndFeelSmart.setFont(Constantes.FONT_NORMAL);
		btnMenuEditarLookAndFeelSmart.addActionListener(new MeuAction(
				"com.jtattoo.plaf.smart.SmartLookAndFeel"));

		switch (Salvar.get("lookandfeel")) {
		case ("com.jtattoo.plaf.acryl.AcrylLookAndFeel"):
			btnMenuEditarLookAndFeelAcryl.setSelected(true);
			break;
		case ("com.jtattoo.plaf.aero.AeroLookAndFeel"):
			btnMenuEditarLookAndFeelAero.setSelected(true);
			break;
		case ("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"):
			btnMenuEditarLookAndFeelBernstein.setSelected(true);
			break;
		case ("com.jtattoo.plaf.hifi.HiFiLookAndFeel"):
			btnMenuEditarLookAndFeelHiFi.setSelected(true);
			break;
		case ("com.jtattoo.plaf.mcwin.McWinLookAndFeel"):
			btnMenuEditarLookAndFeelMcWin.setSelected(true);
			break;
		case ("com.jtattoo.plaf.smart.SmartLookAndFeel"):
			btnMenuEditarLookAndFeelSmart.setSelected(true);
			break;
		}

		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelAcryl);
		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelAero);
		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelBernstein);
		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelHiFi);
		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelMcWin);
		menuEditarLookAndFeel.add(btnMenuEditarLookAndFeelSmart);

		menuEditar.add(menuEditarLookAndFeel);

		barraMenu.add(menuArquivo);
		barraMenu.add(menuEditar);
		barraMenu.add(menuAjuda);

		janela.setJMenuBar(barraMenu);

		janela.getContentPane().add(principal);

		// card.addLayoutComponent(principalLogin, "login");
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}

	public static void main(String[] args) {
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

	private class MeuAction extends AbstractAction {

		private String valor;

		public MeuAction(String valor) {
			this.valor = valor;
		}
		@Override
		
		public void actionPerformed(ActionEvent arg0) {
			Salvar.gravar("lookandfeel", valor);
			reiniciarGui();
		}

	}
}