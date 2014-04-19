package com.codigomestre.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.codigomestre.model.pojo.Sala;

public class ServidorSuporte implements Runnable {

	private Socket cliente;
	private List<Sala> lista;
	private List<UsuarioPOJO> usuarios = new ArrayList<>();

	private static final String CODIGO_CADASTRAR = "cu",
			CODIGO_CADASTRAR_SUCESSO = "scu",
			CODIGO_CADASTRAR_FRACASSO = "fcu", MENSAGEM_EMAIL_DUPLICADO = "ed",
			MENSAGEM_USUARIO_DUPLICADO = "ud";

	public ServidorSuporte(Socket clienteAtual) {
		this.cliente = clienteAtual;
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream obo = new ObjectOutputStream(
					cliente.getOutputStream());
			ObjectInputStream obi = new ObjectInputStream(
					cliente.getInputStream());
			while (true) {
				Properties proRespostas = new Properties();
				Properties pro = (Properties) obi.readObject();

				String s = pro.getProperty("codigo");
				switch (s) {
				case CODIGO_CADASTRAR:
					String nomeUsuario = pro.getProperty("nu"),
					email = pro.getProperty("e");

					boolean sucesso = true;

					for (UsuarioPOJO pojo : usuarios) {
						if (pojo.getEmail().equals(email)) {
							sucesso = false;
							proRespostas.put("mensagem",
									MENSAGEM_EMAIL_DUPLICADO);
							break;
						} else if (pojo.getNomeUsuario().equals(nomeUsuario)) {
							sucesso = false;
							proRespostas.put("mensagem",
									MENSAGEM_USUARIO_DUPLICADO);
							break;
						}
					}

					if (sucesso) {
						usuarios.add(new UsuarioPOJO(nomeUsuario, email));
						proRespostas.put("codigo", CODIGO_CADASTRAR_SUCESSO);
					} else {
						proRespostas.put("codigo", CODIGO_CADASTRAR_FRACASSO);
					}

					obo.writeObject(proRespostas);
					continue;
				case "tc":
					proRespostas.put("codigo", "rtc");

					obo.writeObject(proRespostas);
					continue;
				case "reset":
					usuarios = new ArrayList<>();
					continue;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
