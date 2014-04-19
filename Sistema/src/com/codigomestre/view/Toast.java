package com.codigomestre.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Toast implements Runnable {

	/** 2 segundos */
	public static int RAPIDO = 2000;
	/** 7 segundos */
	public static int DEMORADO = 7000;

	private JFrame janela;
	private String msg;
	private int duracao;
	private JPanel glassPane;
	private JPanel painelMsg;
	private JLabel lbl;
	private Thread t = null;

	public Toast(JFrame janela) {
		this.janela = janela;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public void setMsg(String msg) {
		defPanelSize(msg);
		this.msg = msg;
	}

	private void defPanelSize(String msg) {

	}

	public void iniciarThread() {
		t = new Thread(this);
		t.start();
	}

	public void pararThread() {
		t.stop();
		t = null;
		janela.getGlassPane().setVisible(false);
	}

	public boolean isThreadIniciada() {
		if (t == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void run() {
		if (lbl == null) {
			lbl = new JLabel();
			lbl.setOpaque(false);
			lbl.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		}

		if (glassPane == null) {
			glassPane = new JPanel();
			glassPane.setOpaque(false);
			glassPane.setLayout(new BorderLayout());
			glassPane.add(BorderLayout.SOUTH, lbl);
			janela.setGlassPane(glassPane);
		}

		if (painelMsg == null) {
			painelMsg = new JPanel();
			painelMsg.setBorder(new LineBorder(Color.YELLOW, 3, true));
			painelMsg.setLayout(new BorderLayout());
			painelMsg.add(BorderLayout.CENTER, lbl);
		}

		lbl.setText(msg);

		janela.getGlassPane().setVisible(true);
		janela.getContentPane().repaint();

		try {
			Thread.sleep(duracao);
		} catch (InterruptedException ies) {
		} finally {
			janela.getGlassPane().setVisible(false);
		}
		// Faz a mensagem desaparecer gradualmente
	}

	public static void main(String[] args) {

		String mensagem30c = "123456789123456789123456789123";
		String mensagem60c = "123456789123456789123456789123123456789123456789123456789123";
		String mensagem90c = "123456789123456789123456789123123456789123456789123456789123123456789123456789123456789123";
		String mensagem120c = "123456789123456789123456789123123456789123456789123456789123123456789123456789123456789123123456789123456789123456789123";

		int maxRowChar = 50;
		int horizontalSizeForRow = 300;
		int verticalSizeForRow = 10;

		int linhas30c = 0, linhas60c = 0, linhas90c = 0, linhas120c = 0;

		byte[] mensagem30cArray = mensagem30c.getBytes();

		byte[] mensagem60cArray = mensagem60c.getBytes();

		byte[] mensagem90cArray = mensagem90c.getBytes();

		byte[] mensagem120cArray = mensagem120c.getBytes();

		System.out.println("mensagem30c: " + mensagem30cArray.length
				+ " \\ linhas: " + linhas30c);
		System.out.println("mensagem60c: " + mensagem60cArray.length
				+ " \\ linhas: " + linhas60c);
		System.out.println("mensagem90c: " + mensagem90cArray.length
				+ " \\ linhas: " + linhas90c);
		System.out.println("mensagem120c: " + mensagem120cArray.length
				+ " \\ linhas: " + linhas120c);

	}

}