package com.codigomestre.servidor.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.codigomestre.model.ErroDeConexaoException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * História de usuário sendo implementada HU1C01
 * 
 * @version 1
 * @author Maurício / Ândrei
 */
public class Conexao {

	private static Conexao conexao;
	private static Connection connection;

//	private final static String URL = "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_codigomestre",
//			USER = "b2b3006a278dae", PASSWORD = "3b0bef30";
	private final static String MENSAGEM_ERRO_CONEXAO = "Não foi possível se conectar ao servidor.";
	private final static String MENSAGEM_ERRO_DURANTE_CADASTRO = "Não foi possível se conectar ao servidor.";
	
	private final static String URL_LOCAL = "jdbc:mysql://localhost:3306/codigo_mestre",
			USER_LOCAL = "root", PASSWORD_LOCAL = "";

	public static Conexao getInstance() {

		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao;
	}

	public PreparedStatement getRetornarEstadoDePreparo(String sql)
			throws SQLException, ErroDeConexaoException {
		
		return (PreparedStatement) connection.prepareStatement(sql);
	}

	public void desconectar() throws SQLException {
		connection.close();
	}

	public void conectar() throws SQLException {
		connection = (Connection) DriverManager.getConnection(URL_LOCAL, USER_LOCAL,
					PASSWORD_LOCAL);
	}

}
