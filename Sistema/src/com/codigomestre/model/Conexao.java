package com.codigomestre.model;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

/**
 *     História de usuário sendo implementada HU1C01
 * @version 1
 * @author Maurício / Ândrei
 */
public class Conexao {
	
	private static Conexao conexao;
	private Connection connection;
<<<<<<< HEAD
	private final static String 
		URL="jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_codigomestre",
		USER="b2b3006a278dae",
		PASSWORD="3b0bef30";
	private boolean estaConectado=false;
	private final static String MENSAGEM_ERRO_CONEXAO = "Não foi possível se conectar ao servidor.";
=======
	private final static String URL="jdbc:mysql://localhost:3306/codigo_mestre", USER="root", PASSWORD="";
	private boolean estaConectado=false;
	private final static String MENSAGEM_ERRO_DURANTE_CADASTRO = "Não foi possível se conectar ao servidor.";
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
	
	public static Conexao getInstance() {
		
		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao;		
 	}
<<<<<<< HEAD
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @throws ErroDeConexaoException
	 */
	public PreparedStatement getRetornarEstadoDePreparo( String sql ) throws SQLException, ErroDeConexaoException {
		if (!estaConectado) {
			throw new ErroDeConexaoException(MENSAGEM_ERRO_CONEXAO);
=======
	
	public PreparedStatement getRetornarEstadoDePreparo( String sql ) throws SQLException, ErroDeConexaoException {
		if (!estaConectado) {
			throw new ErroDeConexaoException(MENSAGEM_ERRO_DURANTE_CADASTRO);
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
		}
		
		return (PreparedStatement) connection.prepareStatement(sql) ;
	}

	public void desconectar() throws SQLException {
		connection.close();
<<<<<<< HEAD
		estaConectado = false;
=======
		estaConectado=false;
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
	}
	
	public void conectar() throws SQLException {
		if (!estaConectado) {
<<<<<<< HEAD
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			estaConectado = true;
=======
			connection= (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			estaConectado=true;
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
		}
	}
	
	public boolean estaConectado() {
		return estaConectado;
	}
	

}
