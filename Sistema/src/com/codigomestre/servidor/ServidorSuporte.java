package com.codigomestre.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;

import com.codigomestre.model.ErroCadastroEmailDuplicadoException;
import com.codigomestre.model.ErroCadastroNomeDuplicadoException;
import com.codigomestre.model.ErroDeConexaoException;
import com.codigomestre.model.pojo.Usuario;

public class ServidorSuporte implements Runnable {

	private Socket cliente;
	private static UsuarioDAO uDAO;

	private Properties proRespostas = new Properties();
	
	private static final String CODIGO_CADASTRAR = "cu",
			CODIGO_CADASTRAR_SUCESSO = "scu",
			CODIGO_CADASTRAR_FRACASSO = "fcu",
			CODIGO_RETORNAR_LISTA_USUARIOS = "tc", CODIGO_LOGAR = "l",
			RETORNO_LOGAR_FRACASSO = "fl", 
			RETORNO_LOGIN_SUCESSO = "ls", CODIGO_DESLOGAR = "d";

	public ServidorSuporte(Socket clienteAtual) {
		this.cliente = clienteAtual;
		try {
			uDAO = new UsuarioDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErroDeConexaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
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
					Usuario u = new Usuario(pro.getProperty("nu"),
							pro.getProperty("e"), pro.getProperty("s"));
					
					try  {
						if (uDAO.isOnline(u)) {
							proRespostas.put("codigo", RETORNO_LOGAR_FRACASSO);
							proRespostas.put("mensagem", "O usuário já está online.");
						} else {
							uDAO.logar(u);
							// @TODO revisar linha sweguinte - Retornar info sobre o usuário e dos demais usuários.
							proRespostas.put("codigo", RETORNO_LOGIN_SUCESSO);
						}
					} catch (SQLException | ErroDeConexaoException as) {
						System.out.println(as.getMessage());
					}
						
					obo.writeObject(proRespostas);
					break;
					// DESLOGAR
				case CODIGO_DESLOGAR:
					
					break;
					// RESET
				case "reset":
					uDAO.reset();
					break;
				}
			}

		} catch (Exception e) {
		}

	}
}
