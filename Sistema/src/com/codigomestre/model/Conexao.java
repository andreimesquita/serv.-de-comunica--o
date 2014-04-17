package com.codigomestre.model;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 *     História de usuário sendo implementada HU1C01
 * @version 1
 * @author Maurício / Ândrei
 */
public class Conexao {
	
	private static Conexao conexao;
	private Connection connection;

	private final static String 
		URL="jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_codigomestre",
		USER="b2b3006a278dae",
		PASSWORD="3b0bef30";
	private boolean estaConectado=false;
	private final static String MENSAGEM_ERRO_CONEXAO = "Não foi possível se conectar ao servidor.";

	private final static String URL_LOCAL="jdbc:mysql://localhost:3306/codigo_mestre", USER_LOCAL="root", PASSWORD_LOCAL="";
	private final static String MENSAGEM_ERRO_DURANTE_CADASTRO = "Não foi possível se conectar ao servidor.";
	
	public static Conexao getInstance() {
		
		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao;		
 	}

	public PreparedStatement getRetornarEstadoDePreparo( String sql ) throws SQLException, ErroDeConexaoException {
		if (!estaConectado) {
			throw new ErroDeConexaoException(MENSAGEM_ERRO_DURANTE_CADASTRO);

		}
		
		return (PreparedStatement) connection.prepareStatement(sql) ;
	}

	public void desconectar() throws SQLException {
		connection.close();

		estaConectado = false;
	}
	
	public void conectar() throws SQLException {
		if (!estaConectado) {

			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			estaConectado = true;

		}
	}
	
	public boolean estaConectado() {
		return estaConectado;
	}
	

}
