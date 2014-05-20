package com.codigomestre.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;

import com.codigomestre.cliente.view.SalaView;
import com.codigomestre.cliente.view.SalasAbasView;
import com.codigomestre.servidor.ServidorSuporte;

public class Cliente extends Thread {

	private ObjectInputStream ois;

	private SalasAbasView sav;

	public Cliente(InputStream is) throws Exception {
		this.ois = new ObjectInputStream(is);
		sav = SalasAbasView.getInstance();
	}

	public void desconectar() throws IOException {
		ois.close();
		stop();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Properties p = (Properties) ois.readObject();
				String codigo = p.getProperty("codigo");
				System.out.println("CLIENTE RECEBIDO: " + codigo);
				switch (codigo) {
				case (ServidorSuporte.RETORNO_LOGIN_SUCESSO):
					System.out.println("Usuário logado com sucesso!");
					break;
				case (ServidorSuporte.RETORNO_DESLOGAR):
					System.out.println("Usuário deslogado com sucesso!");
					break;
				case (ServidorSuporte.RETORNO_ENTRAR_SALA_SUCESSO):
					System.out.println("CLIENTE: O USUÁRIO ENTROU NA SALA!");
					String nomeSala = p.getProperty("ns");
					sav.adicionarSala(new SalaView(nomeSala), nomeSala);
					// TODO: Abrir o bate papo da sala
					break;
				case (ServidorSuporte.MENSAGEM_RECEBIDA):
					System.out.println("CLIENTE: MENSAGEM RECEBIDA");
					String ns = p.getProperty("ns");
					String mensagem = p.getProperty("m");
					sav.escrever(ns, mensagem);
					break;
				}
			} catch (Exception e) {
			}
		}
	}
}