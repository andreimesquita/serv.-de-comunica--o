package com.codigomestre.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.codigomestre.model.pojo.Usuario;
import com.mysql.jdbc.PreparedStatement;
/**
 *     História de usuário sendo implementada HU1C01, C02, C03, C04
 * @version 2
 * @author Maurício / Ândrei
 */
public class UsuarioDAO {

	private static final String SQL_CRIAR_TABELA_USUARIO = "create table usuarios("
			+ "nome varchar(50) primary key,"
			+ "email varchar(50),"
			+ "senha varchar(16)"
			+ ");";

	private static final String SQL_TESTE_CADASTRO_EMAIL_EXISTE = "select * from usuarios where email=?";
	private static final String SQL_TESTE_CADASTRO_NOME_EXISTE = "select * from usuarios where nome=?";
	private static final String MENSAGEM_ERRO_EMAIL_EXISTENTE = "O e-mail selecionado já está em uso.";
	private static final String MENSAGEM_ERRO_NOME_EXISTENTE = "O usuário selecionado já está em uso.";
	private static final String TABELA_USUARIOS_NOME = "usuarios";
	
	
	
	private Conexao conexao;
	
	
	public UsuarioDAO () throws SQLException, ErroDeConexaoException {
		this.conexao = Conexao.getInstance();
		this.conexao.conectar();
		this.conexao.getRetornarEstadoDePreparo(SQL_CRIAR_TABELA_USUARIO);
	}
	
	public void cadastrar( Usuario u ) throws ErroCadastroEmailDuplicadoException, ErroCadastroNomeDuplicadoException, ErroDeConexaoException, SQLException {		
		
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo(SQL_TESTE_CADASTRO_EMAIL_EXISTE); 
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		boolean b = false;
		while ( objrs.next() ) {
			b=true;
		}
		if ( b ) {
			throw new ErroCadastroEmailDuplicadoException(MENSAGEM_ERRO_EMAIL_EXISTENTE);
		}
		
		objprep = conexao.getRetornarEstadoDePreparo(SQL_TESTE_CADASTRO_NOME_EXISTE); 
		objprep.setString(1, u.getNomeUsuario());
		objrs = objprep.executeQuery();
		b = false;
		while ( objrs.next() ) {
			b=true;
		}
		if ( b ) {
			throw new ErroCadastroNomeDuplicadoException(MENSAGEM_ERRO_NOME_EXISTENTE);
		}
		
	}
	
	public void reset() throws SQLException, ErroDeConexaoException {
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo( "truncate table ".concat(TABELA_USUARIOS_NOME)); 
		objprep.execute();
	}
	
}
