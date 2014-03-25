package com.codigomestre.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.codigomestre.model.pojo.Usuario;
import com.mysql.jdbc.PreparedStatement;
/**
 *     História de usuário sendo implementada HU1C01
 * @version 1
 * @author Maurício / Ândrei
 */
public class CadastroUsuario {

	private static final String SQL_TESTE_CADASTRO_EMAIL_EXISTE = "select * from usuarios where email=?";
	private static final String MENSAGEM_ERRO_EMAIL_EXISTENTE = "Email já cadastrado.";
	private static final String TABELA_USUARIOS_NOME = "usuarios";
	
	
	
	private Conexao conexao;
	
	
	public CadastroUsuario () {
		this.conexao = Conexao.getInstance();
	}
	
	public void cadastrar( Usuario u ) throws ErroDuranteCadastroException, ErroDeConexaoException, SQLException {		
		
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo(SQL_TESTE_CADASTRO_EMAIL_EXISTE); 
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		boolean b = false;
		while ( objrs.next() ) {
			b=true;
		}
		if ( b ) {
			throw new ErroDuranteCadastroException(MENSAGEM_ERRO_EMAIL_EXISTENTE);
		}
		
	}
	
	public void reset() throws SQLException, ErroDeConexaoException {
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo( "truncate table ".concat(TABELA_USUARIOS_NOME)); 
		objprep.execute();
	}
	
}
