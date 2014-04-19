package com.codigomestre.model;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import com.codigomestre.model.pojo.Sala;

/**
 * HU3C01 Version 1
 * 
 * @author Ândrei
 */
public class SalaTest extends TestCase {

	@Test
	public void testEnviarMensagemNaSala() {
		Calendar c = Calendar.getInstance();
		String nome = "Alberto Bins";
		String mensagem = "Aee";
		Sala.escrever(nome, mensagem);
		Date data = c.getTime();
		mensagem = nome + " [" + data.getHours() + "h:" + data.getMinutes()
				+ "m]:" + mensagem;
		assertEquals(mensagem, Sala.getUltimaMensagem());
	}
}