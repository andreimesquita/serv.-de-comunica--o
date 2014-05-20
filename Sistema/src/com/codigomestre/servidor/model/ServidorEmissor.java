package com.codigomestre.servidor.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

public abstract class ServidorEmissor {

	private ObjectOutputStream objout;
	protected Properties prop;
	
	public ServidorEmissor(ObjectOutputStream ob) {
		this.objout=ob;
		prop = new Properties();
	}
	
	public void enviar() throws IOException {
		objout.writeObject(prop);
	}
	
	public abstract void def(String ... valores);
	
}
