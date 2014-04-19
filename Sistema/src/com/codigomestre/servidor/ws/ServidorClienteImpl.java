package com.codigomestre.servidor.ws;

import java.util.Properties;

import javax.jws.WebService;

@WebService(endpointInterface = "com.codigomestre.servidor.ws.ServidorClienteImpl")
public class ServidorClienteImpl implements ServidorCliente {

	@Override
	public Properties cadastro(Properties properties) {
		String usuario = properties.getProperty("usuario");
		String email = properties.getProperty("email");
		String senha = properties.getProperty("senha");
		if (usuario.equals(usuario)) {

		}

		return null;
	}

}