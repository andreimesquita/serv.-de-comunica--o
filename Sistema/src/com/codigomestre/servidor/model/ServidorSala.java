package com.codigomestre.servidor.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta classe deve ser vista pelo Servidor.
 */
public class ServidorSala {
	
		private HashMap<String, ServidorEmissor> emissores;

		/** Lista contendo o identificador(nome) dos usuarios contidos na sala */
		private ArrayList<String> nomesUsuariosNaSala;
		
		/**
		 * Método construtor da classe ServidorSala,
		 * é neste metodo que a lista contendo o identificador(nome) dos usuarios contidos na sala é iniciada.
		 */
	public ServidorSala() {
		nomesUsuariosNaSala = new ArrayList<>();
	}
	/**
	 * Este método adiciona um novo usuario na sala.
	 * @param nome identificador do usuario que será adicionado na sala.
	 * TODO: os outros usuario que estiverem online na sala precisam ser avisados quando este novo usuario for adicionado.
	 */
	public void adicionarNovoUsuarioNaSala(String nome, ObjectOutputStream obout) {
		nomesUsuariosNaSala.add(nome);
//		emissores.put(nome, (ServidorEmissor) new ServidorSalaEmissor(obout) );	
	}
	
	/**
	 * Este método verifica se o identificador(nome) do usuario ja está contido na lista.
	 * @param nome identificador do usuario a ser pesquisado.
	 * @return true se o usuario estiver contido na lista, e false do contrário.
	 */
	public boolean isUsuarioJaNaSala(String nome) {
		
		return false;
	}
	
	public void enviarMensagem() {
		for (String string:nomesUsuariosNaSala) {
			emissores.get(string).def("AS");
			try {
				emissores.get(string).enviar();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
