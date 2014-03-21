package com.codigomestre.model;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 *     Hist�ria de usu�rio sendo implementada HU1C01
 * @version 1
 * @author Maur�cio / �ndrei
 */
public class Conexao {
	
	private static Conexao conexao;
	private Connection connection;
	private final static String URL="jdbc:mysql://localhost:3306/codigomestre", USER="root", PASSWORD="root";
	
	
	public Conexao() throws SQLException {
		
		connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
	}



	public static Conexao getInstance() throws SQLException {
		
		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao;		
 	}
	
	public PreparedStatement getRetornarEstadoDePreparo( String sql ) throws SQLException {
		return (PreparedStatement) connection.prepareStatement(sql) ;
	}
	
	
	

}
