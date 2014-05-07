package com.codigomestre.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.codigomestre.action.LoginAction;
import com.codigomestre.action.ToRegisterViewAction;

public class InicialView extends JPanel {

	private JLabel lblUser, lblPass;
	private JTextField txfUser, txfPass;
	private JButton btnLogin, btnRegister;
	private JPanel externo;

	public InicialView() {
		this.lblUser = new JLabel("Usuário:");
		lblUser.setFont(Constantes.FONT_NORMAL);
		this.lblPass = new JLabel("Senha:");
		lblPass.setFont(Constantes.FONT_NORMAL);
		this.txfUser = new JTextField();
		txfUser.setFont(Constantes.FONT_NORMAL);
		ClicEnter clic = new ClicEnter();
		txfUser.addKeyListener(clic);

		this.txfPass = new JTextField();
		txfPass.setFont(Constantes.FONT_NORMAL);
		txfPass.addKeyListener(clic);
		this.btnLogin = new JButton();
		btnLogin.setFont(Constantes.FONT_BIG);
		btnLogin.addActionListener(new LoginAction(this));
		btnLogin.setText("Logar");
		this.btnRegister = new JButton();
		btnRegister.setFont(Constantes.FONT_BIG);
		btnRegister.addActionListener(new ToRegisterViewAction(this));
		btnRegister.setText("Registrar");

		externo = new JPanel();
		externo.setLayout(new GridLayout(3, 2));
		externo.add(lblUser);
		externo.add(txfUser);
		externo.add(lblPass);
		externo.add(txfPass);
		externo.add(btnLogin);
		externo.add(btnRegister);
		
		setLayout(new FlowLayout());
		add(externo);
	}

	public String getUserValue() {
		return txfUser.getText().toString();
	}

	public String getPassValue() {
		return txfPass.getText().toString();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setContentPane(new InicialView());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private class ClicEnter implements KeyListener {

		@Override
		public void keyTyped(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				btnLogin.doClick();
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {}
	}

}
