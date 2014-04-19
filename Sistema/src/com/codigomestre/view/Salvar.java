package com.codigomestre.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Salvar {
	private static final String ARQUIVO_DEFINICAO = "definicao.xml";

	public static String get(String valor) {
		String resultado = null;
		FileInputStream fis = null;
		try {
			File f = new File(ARQUIVO_DEFINICAO);
			fis = new FileInputStream(f);
			Properties propriedades = new Properties();
			propriedades.load(fis);
			resultado = propriedades.getProperty(valor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception es) {
					es.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public static void gravar(String propriedade, String valor) {
		File f = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Properties propriedades = null;
		try {
			f = new File(ARQUIVO_DEFINICAO);
			fis = new FileInputStream(f);
			fos = new FileOutputStream(f);

			propriedades = new Properties();
			propriedades.load(fis);
			propriedades.setProperty(propriedade, valor);
			propriedades.store(fos, ARQUIVO_DEFINICAO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}

				if (fis != null) {
					fis.close();
				}
			} catch (Exception es) {
				es.printStackTrace();
			}

		}
	}
}
