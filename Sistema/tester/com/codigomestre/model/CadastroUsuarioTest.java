package com.codigomestre.model;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.pojo.Usuario;

/**
 * História de usuário sendo implementada HU1C01
 * 
 * @version 1
 * @author Maurício
 */
public class CadastroUsuarioTest extends TestCase {
	
	private CadastroUsuario cadastro = new CadastroUsuario() ;
	
	
	@Before
	public void setUp() throws Exception {
		Conexao.getInstance().conectar();
		cadastro.reset();
	}

	@Test	
	public void testeCriacaoDeUsuarioComSucesso()
			throws ErroDuranteCadastroException, SQLException, ErroDeConexaoException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		cadastro.cadastrar(user);
	}

	@Test(expected = ErroDuranteCadastroException.class)
	public void testeCriacaoDeUsuarioDuplicata()
			throws ErroDuranteCadastroException, SQLException, ErroDeConexaoException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		cadastro.cadastrar(user);
		cadastro.cadastrar(user);
	}
	
	@Test ( expected = ErroDeConexaoException.class )
	public void testErroConexaoComServidor() throws SQLException, ErroDeConexaoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		Conexao conexao = Conexao.getInstance();
		conexao.desconectar();
		cadastro.cadastrar(user);
		
	}
	
	
	
}