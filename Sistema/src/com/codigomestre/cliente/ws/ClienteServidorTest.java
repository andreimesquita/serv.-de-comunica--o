package com.codigomestre.cliente.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.codigomestre.servidor.ws.HelloWorld;

public class ClienteServidorTest {
	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:9000/ws/hello?wsdl");
		QName qname = new QName("http://ws.servidor.codigomestre.com/",
				"HelloWorldImplService");

		Service service = Service.create(url, qname);
		HelloWorld hello = service.getPort(HelloWorld.class);

		System.out.println(hello.getHelloWorldMessage("Ândrei"));
		System.out.println();
		System.out.println(hello.getMyName("Ândrei"));

	}
}
