package com.codigomestre.model;

import java.sql.SQLException;
<<<<<<< HEAD
import com.codigomestre.model.*;
=======
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea

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
	
<<<<<<< HEAD
	private UsuarioDAO cadastro = new UsuarioDAO() ;
=======
	private CadastroUsuario cadastro = new CadastroUsuario() ;
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
	
	
	@Before
	public void setUp() throws Exception {
		Conexao.getInstance().conectar();
		cadastro.reset();
	}

	@Test	
	public void testeCriacaoDeUsuarioComSucesso()
<<<<<<< HEAD
			throws ErroCadastroNomeDuplicadoException, ErroCadastroEmailDuplicadoException, SQLException, ErroDeConexaoException {
=======
			throws ErroDuranteCadastroException, SQLException, ErroDeConexaoException {
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		cadastro.cadastrar(user);
	}

<<<<<<< HEAD
	@Test(expected = ErroCadastroNomeDuplicadoException.class)
	public void testeCriacaoDeUsuarioDuplicataNome()
			throws ErroCadastroNomeDuplicadoException, ErroCadastroEmailDuplicadoException, SQLException, ErroDeConexaoException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		cadastro.cadastrar(user);
		Usuario usertwo = new Usuario("AndreiRS", "andrei@outlook.com", "123"); 
		cadastro.cadastrar(usertwo);
	}
	
	@Test(expected = ErroCadastroEmailDuplicadoException.class)
	public void testeCriacaoDeUsuarioDuplicataEmail()
			throws ErroCadastroEmailDuplicadoException, ErroCadastroNomeDuplicadoException, SQLException, ErroDeConexaoException {
		Usuario user = new Usuario("mauriciocarvalho", "mauriciocarvalho@outlook.com", "123"); 
		cadastro.cadastrar(user);
		Usuario usertwo = new Usuario("mauricio_carvalho", "mauriciocarvalho@outlook.com", "123");
		cadastro.cadastrar(usertwo);
	}
	
	/**
	 * Exceção enviada não é recebida corretamente pela teste.
	 */
	public void tstErroConexaoComServidor() throws ErroDeConexaoException, SQLException, ErroCadastroNomeDuplicadoException, ErroCadastroEmailDuplicadoException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123"); 
		Conexao conexao = Conexao.getInstance();
		conexao.desconectar();
		cadastro.cadastrar(user);
	}
		
=======
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
	
	
>>>>>>> 454f15b7e3954302d9aba45d6efb4e9f092fbeea
	
}