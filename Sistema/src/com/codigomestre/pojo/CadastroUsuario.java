package com.codigomestre.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import banco.de.dados.Conexao;

import com.mysql.jdbc.PreparedStatement;

public class CadastroUsuario {

	private static final String SQL_TESTE_CADASTRO_EMAIL_EXISTE = "select * from usuarios where email=?";
	private Conexao conexao;
	
	
	public CadastroUsuario () {
		try {
			this.conexao = Conexao.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cadastrar( Usuario u ) throws ErroDuranteCadastroException, SQLException {		
		
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo(SQL_TESTE_CADASTRO_EMAIL_EXISTE); 
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		objrs.get
		
		if (u != null) {
			throw new ErroDuranteCadastroException("Email já cadastrado.");
		}
	}
	
	public static void reset() {
		lista.clear();
	}
	
}
