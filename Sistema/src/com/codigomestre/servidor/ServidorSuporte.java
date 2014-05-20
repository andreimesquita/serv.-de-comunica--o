package com.codigomestre.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

import com.codigomestre.model.ErroCadastroEmailDuplicadoException;
import com.codigomestre.model.ErroCadastroNomeDuplicadoException;
import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.dao.UsuarioDAO;
import com.codigomestre.servidor.model.ServidorSala;

public class ServidorSuporte implements Runnable {

	private Socket cliente;

	private static UsuarioDAO uDAO;

	private Usuario u;

	private Properties proRespostas = new Properties();

	private static HashMap<String, ServidorSala> salas;

	public static final String CODIGO_CADASTRAR = "cu",
			CODIGO_CADASTRAR_SUCESSO = "scu",
			CODIGO_CADASTRAR_FRACASSO = "fcu",
			CODIGO_RETORNAR_LISTA_USUARIOS = "tc", CODIGO_LOGAR = "l",
			RETORNO_LOGAR_FRACASSO = "fl", RETORNO_LOGIN_SUCESSO = "ls",
			CODIGO_DESLOGAR = "d", RETORNO_DESLOGAR = "rd",
			CODIGO_ENTRAR_SALA = "es", CODIGO_SAIR_SALA = "ss",
			RETORNO_ENTRAR_SALA_FRACASSO = "esf",
			RETORNO_ENTRAR_SALA_SUCESSO = "ess", MENSAGEM_RECEBIDA = "mr";

	private static final String[] nomeSalas = { "Android", "Arduino", "C/C++",
			"C#", "HTML", "Java", "JavaScript", "JQuery", "Python", "PHP",
			"Ruby" };
	
	public ServidorSuporte(Socket clienteAtual) {
		this.cliente = clienteAtual;
		// try {
		// if (uDAO == null) {
		// uDAO = new UsuarioDAO();
		// }
		if (salas == null) {
			salas = new HashMap<>();
			for (String string : nomeSalas) {
				salas.put(string, new ServidorSala());
			}
			// Para X de 0 até 10 Faça

			// Para cada nomeSalas Faça
		}
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (ErroDeConexaoException e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream obo = new ObjectOutputStream(
					cliente.getOutputStream());
			ObjectInputStream obi = new ObjectInputStream(
					cliente.getInputStream());
			Properties pro;
			while (true) {
				proRespostas = new Properties();

				pro = (Properties) obi.readObject();

				String s = pro.getProperty("codigo");
				switch (s) {
				// CADASTRO
				case CODIGO_CADASTRAR:
					Usuario usuario = new Usuario(pro.getProperty("nu"),
							pro.getProperty("e"), pro.getProperty("s"));

					boolean sucesso = true;

					try {
						uDAO.cadastrar(usuario);
					} catch (ErroCadastroNomeDuplicadoException ecnde) {
						sucesso = false;
						proRespostas.put("mensagem", ecnde.getMessage());
					} catch (ErroCadastroEmailDuplicadoException ecede) {
						sucesso = false;
						proRespostas.put("mensagem", ecede.getMessage());
					}

					if (sucesso) {
						proRespostas.put("codigo", CODIGO_CADASTRAR_SUCESSO);
					} else {
						proRespostas.put("codigo", CODIGO_CADASTRAR_FRACASSO);
					}

					obo.writeObject(proRespostas);
					break;
				// RETORNAR LISTA DE USUÁRIO
				case CODIGO_RETORNAR_LISTA_USUARIOS:
					proRespostas.put("codigo", "rtc");
					obo.writeObject(proRespostas);
					break;
				// LOGIN
				case CODIGO_LOGAR:
					System.out.println("LOGAR");
					u = new Usuario(pro.getProperty("nu"),
							pro.getProperty("e"), pro.getProperty("s"));

					// try {
					// if (uDAO.isOnline(u)) {
					// proRespostas.put("codigo", RETORNO_LOGAR_FRACASSO);
					// proRespostas.put("mensagem",
					// "O usuário já está online.");
					// u = null;
					// } else {
					// uDAO.logar(u);
					// // @TODO revisar linha sweguinte - Retornar info
					// // sobre o usuário e dos demais usuários.
					//
					// }
					// } catch (SQLException | ErroDeConexaoException as) {
					// System.out.println(as.getMessage());
					// }
					proRespostas.put("codigo", RETORNO_LOGIN_SUCESSO);
					obo.writeObject(proRespostas);
					break;
				// DESLOGAR
				case CODIGO_DESLOGAR:
					Usuario us = new Usuario(pro.getProperty("nu"),
							pro.getProperty("e"), pro.getProperty("s"));
					if (uDAO.isOnline(us)) {
						uDAO.deslogar(us);
						proRespostas.put("codigo", RETORNO_DESLOGAR);
					} else {
						// O usuário não está online
					}

					obo.writeObject(proRespostas);
					break;
				// RESET
				case CODIGO_ENTRAR_SALA:

					// TODO: O usuário é inserido na sala e o servidor notifica
					// o usuário de que a entrada foi possível.
					String nomeSala = pro.getProperty("ns");

					ServidorSala ss = salas.get(nomeSala);

					ss.adicionarNovoUsuarioNaSala(
							u.getNomeUsuario(),
							obo);

					proRespostas.put("codigo", RETORNO_ENTRAR_SALA_SUCESSO);
					proRespostas.put("ns", nomeSala);
					
					obo.writeObject(proRespostas);

					proRespostas.clear();
					proRespostas = new Properties();
					proRespostas.put("codigo", MENSAGEM_RECEBIDA);
					proRespostas.put("ns", nomeSala);
					proRespostas.put("m", "Seja Bem Vindo(a) a sala " + nomeSala + "!");

					obo.writeObject(proRespostas);

					break;
				case "reset":
					uDAO.reset();
					break;
				}
			}

		} catch (Exception e) {
		}

	}

}
