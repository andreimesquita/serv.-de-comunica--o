package com.codigomestre;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.codigomestre.model.CadastroUsuario;
import com.codigomestre.model.ErroCadastroEmailDuplicadoException;
import com.codigomestre.model.ErroCadastroNomeDuplicadoException;
import com.codigomestre.model.ErroDeConexaoException;
import com.codigomestre.model.ErroDuranteCadastroException;
import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.dao.Conexao;
import com.codigomestre.servidor.dao.UsuarioDAO;

/**
 * História de usuário sendo implementada HU1C01
 * @deprecated Estes testes estão na classe UsuarioDAOTest.
 * @version 1
 * @author Maurício
 */
public class CadastroUsuarioTest extends TestCase {

	private UsuarioDAO usuario;
	private CadastroUsuario cadastro = new CadastroUsuario();

	@Before
	public void setUp() throws Exception {
		Conexao.getInstance().conectar();
		if (usuario == null) {
			usuario = new UsuarioDAO();
		}
		cadastro.reset();
	}

	@Test
	public void testeCriacaoDeUsuarioComSucesso()
			throws ErroCadastroNomeDuplicadoException,
			ErroCadastroEmailDuplicadoException, SQLException,
			ErroDeConexaoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123");
		cadastro.cadastrar(user);
	}

	@Test(expected = ErroCadastroNomeDuplicadoException.class)
	public void testeCriacaoDeUsuarioDuplicataNome()
			throws ErroCadastroNomeDuplicadoException,
			ErroCadastroEmailDuplicadoException, SQLException,
			ErroDeConexaoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123");
		cadastro.cadastrar(user);
		Usuario usertwo = new Usuario("AndreiRS", "andrei@outlook.com", "123");
		cadastro.cadastrar(usertwo);
	}

	@Test(expected = ErroCadastroEmailDuplicadoException.class)
	public void testeCriacaoDeUsuarioDuplicataEmail()
			throws ErroCadastroEmailDuplicadoException,
			ErroCadastroNomeDuplicadoException, SQLException,
			ErroDeConexaoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("mauriciocarvalho",
				"mauriciocarvalho@outlook.com", "123");
		cadastro.cadastrar(user);
		Usuario usertwo = new Usuario("mauricio_carvalho",
				"mauriciocarvalho@outlook.com", "123");
		cadastro.cadastrar(usertwo);
	}

	/**
	 * Exceção enviada não é recebida corretamente pela teste.
	 * 
	 * @throws ErroDuranteCadastroException
	 */
	public void tstErroConexaoComServidor() throws ErroDeConexaoException,
			SQLException, ErroCadastroNomeDuplicadoException,
			ErroCadastroEmailDuplicadoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123");
		Conexao conexao = Conexao.getInstance();
		conexao.desconectar();
		cadastro.cadastrar(user);
	}

	@Test(expected = ErroDuranteCadastroException.class)
	public void testeCriacaoDeUsuarioDuplicata()
			throws ErroDuranteCadastroException, SQLException,
			ErroDeConexaoException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123");
		cadastro.cadastrar(user);
		cadastro.cadastrar(user);
	}

	@Test(expected = ErroDeConexaoException.class)
	public void testErroConexaoComServidor() throws SQLException,
			ErroDeConexaoException, ErroDuranteCadastroException {
		Usuario user = new Usuario("AndreiRS", "andreirs@outlook.com", "123");
		Conexao conexao = Conexao.getInstance();
		conexao.desconectar();
		cadastro.cadastrar(user);

	}

}