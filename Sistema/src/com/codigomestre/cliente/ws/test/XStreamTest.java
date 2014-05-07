package com.codigomestre.cliente.ws.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * You require xstream-[version].jar, xpp3-[version].jar and
 * xmlpull-[version].jar in the classpath.
 *
 * Xpp3 is a very fast XML pull-parser implementation.
 */
public class XStreamTest {
	public static void main(String[] args) {

		XStream xstream = new XStream();

		// Does not require XPP3 library
		XStream xstreamDOM = new XStream(new DomDriver());

		// Does not require XPP3 library starting with Java 6
		XStream xstreamStax = new XStream(new StaxDriver());

		Objeto objeto = new Objeto("Ândrei", "andreirs@outlook.com");
		long initXstream = System.nanoTime();
		String xml = xstream.toXML(objeto);
		long endXstream = System.nanoTime();

		long result = (endXstream - initXstream);
		
		System.out
		.println("Serializing an object to XML:  time : "
				+ result + "\n\n" + xml);

		long initXstreamDOM = System.nanoTime();
		String xmlDOM = xstreamDOM.toXML(objeto);
		long endXstreamDOM = System.nanoTime();

		long resultDOM = (endXstreamDOM - initXstreamDOM);
		
		System.out
		.println("\n\nSerializing an object to XML with DOM:  time : "
				+ resultDOM + "\n\n" + xmlDOM);

		long initXstreamStax = System.nanoTime();
		String xmlStax = xstreamStax.toXML(objeto);
		long endXstreamStax = System.nanoTime();

		long resultStax = (endXstreamStax - initXstreamStax);
		
		System.out
				.println("\n\nSerializing an object to XML with STAX:  time : "
						+ resultStax + "\n\n" + xmlStax + "\n\t");
		
		if (result < resultDOM) {
			if (resultStax < result) {
				System.out.println("MAIS RÁPIDO: xstream (STAX)");
			} else {
				System.out.println("MAIS RÁPIDO: xstream (padrão)");
			}
		} else {
			if (resultStax < resultDOM) {
				System.out.println("MAIS RÁPIDO: xstream (STAX)");
			} else {
				System.out.println("MAIS RÁPIDO: xstream (DOM)");
			}
		}
		
		// Serializing an object to XML
		// Objeto objeto = new Objeto("Joe", "Walnes");
		// String xml = xstream.toXML(objeto);

		// Deserializing an object back from XML
		// Objeto d = (Objeto) xstream.fromXML(xml);

	}

}