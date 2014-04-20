package com.codigomestre.servidor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codigomestre.model.ErroCadastroEmailDuplicadoException;
import com.codigomestre.model.ErroCadastroNomeDuplicadoException;
import com.codigomestre.model.ErroDeConexaoException;
import com.codigomestre.model.pojo.Usuario;
import com.codigomestre.servidor.exception.LoginEmailIncorretoException;
import com.codigomestre.servidor.exception.LoginSenhaIncorretaException;
import com.mysql.jdbc.PreparedStatement;

/**
 * Esta classe utiliza o padrão DAO e é responsável pelo CRUD na tabela usuarios.
 * 
 * Implementado
 *     História de usuário sendo implementada HU1C01, C02, C03, C04
 * 
 *     nomeUsuario / email / senha / online
 * 
 * @version 2.0
 * @author Maurício / Ândrei
 */
public class UsuarioDAO {
	/**
	 * @deprecated SQL de criação da tabela <i>usuarios</i>. Não há mais
	 *             utilizade de utilização daqui para a frente.
	 */
	private static final String SQL_CRIAR_TABELA_USUARIO = "create table usuarios ("
			+ "nome varchar(50) primary key,"
			+ "email varchar(50),"
			+ "senha varchar(16)" + ");";
	/**
	 * Retorna o estado online de um usuário selecionado buscando pelo seu
	 * email. <b>SQL:</b> <i>"select online from usuarios where email = ?"</i>
	 */
	private static final String SQL_ONLINE_POR_EMAIL = "select online from usuarios where email = ?";
	/**
	 * Muda o estado do usuário para <b>online</b> através do email e senha do mesmo.
	 * <b>SQL:</b>
	 * <i>"update usuarios set online = true where email = ? AND senha = ?"</i>
	 */
	private static final String SQL_SET_ONLINE = "update usuarios set online = true where email = ? AND senha = ?";
	/**
	 * Muda o estado do usuário para <b>offline</b> através do email e senha do mesmo.
	 * <b>SQL:</b>
	 * <i>"update usuarios set online = false where email = ? AND senha = ?"</i>
	 */
	private static final String SQL_SET_OFFLINE = "update usuarios set online = false where email = ? AND senha = ?";
	/**
	 * Retorna um usuário buscando por email e senha. <b>SQL:</b>
	 * <i>"SELECT * FROM usuarios WHERE email = ? AND senha = ?"</i>
	 */
	private static final String SQL_LOGIN = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
	/**
	 * Insere um novo usuário. <b>SQL:</b>
	 * <i>"insert into usuarios (nome,email,senha) values (?,?,?)"</i>
	 */
	private static final String SQL_INSERIR_USUARIO = "insert into usuarios (nome,email,senha) values (?,?,?)";
	/**
	 * Retorna um usuário buscando pelo seu email. <b>SQL:</b>
	 * <i>"select * from usuarios where email=?"</i>
	 */
	private static final String SQL_RETORNAR_POR_EMAIL = "select * from usuarios where email=?";
	/**
	 * Retorna todos os usuarios na tabela. <b>SQL:</b>
	 * <i>"select * from usuarios"</i>
	 */
	private static final String SQL_RETORNAR_TUDO = "select * from usuarios";
	/**
	 * Retorna um usuário buscando pelo seu nome. <b>SQL:</b>
	 * <i>"select * from usuarios where nome=?"</i>
	 */
	private static final String SQL_TESTE_CADASTRO_NOME_EXISTE = "select * from usuarios where nome=?";
	/**
	 * Mensagem de erro caso o email já exista. Utilizada durante o cadastro de
	 * novo usuário. <b>MENSAGEM:</b>
	 * <i>"O e-mail selecionado já está em uso."</i>
	 */
	private static final String MENSAGEM_ERRO_EMAIL_EXISTENTE = "O e-mail selecionado já está em uso.";
	/**
	 * Mensagem de erro caso o nome do usuário já exista. Utilizada durante o
	 * cadastro de novo usuário. <b>MENSAGEM:</b>
	 * <i>"O usuário selecionado já está em uso."</i>
	 */
	private static final String MENSAGEM_ERRO_NOME_EXISTENTE = "O usuário selecionado já está em uso.";
	/**
	 * TODO Escrever mensagem de erro para quando a senha estiver incorreta.
	 * Utilizado durante logon.
	 */
	private static final String MENSAGEM_ERRO_SENHA_INCORRETA = "";
	/**
	 * TODO Escrever mensagem de erro para quando e email não constar na base de
	 * dados. Utilizado durante logon.
	 */
	private static final String MENSAGEM_ERRO_EMAIL_INEXISTENTE = "";
	/**
	 * Este é o nome da tabela na qual este DAO trabalha.
	 */
	private static final String TABELA_USUARIOS_NOME = "usuarios";
	/**
	 * Classe responsável pela conexão com o banco de dados.
	 */
	private Conexao conexao;

	/**
	 * Construtor padrão que inicializa a conexão com o banco de dados.
	 * 
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 * @throws SQLException
	 *             O banco de dados não foi iniciado corretamente devido a url,
	 *             senha ou login incorreto.
	 */
	public UsuarioDAO() throws SQLException, ErroDeConexaoException {
		this.conexao = Conexao.getInstance();
		this.conexao.conectar();
	}

	/**
	 * Realiza os testes necessários para saber se o email ou nome de usuário já
	 * consta na base de dados e cadastra o novo usuário caso o mesmo passe nos
	 * testes. Este método retorna exceções caso o email ou o nome de usuário já
	 * existam no banco de dados.
	 * 
	 * @param u
	 * @throws ErroCadastroEmailDuplicadoException
	 *             o email já existe na base de dados.
	 * @throws ErroCadastroNomeDuplicadoException
	 *             O nome de usuário já existe na base de dados.
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 * @throws SQLException
	 *             A SQL não está correta.
	 */
	public synchronized void cadastrar(Usuario u)
			throws ErroCadastroEmailDuplicadoException,
			ErroCadastroNomeDuplicadoException, ErroDeConexaoException,
			SQLException {

		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_RETORNAR_POR_EMAIL);
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		boolean b = false;

		objrs.last();

		if (objrs.getRow() == 1) {
			b = true;
		}

		if (b) {
			throw new ErroCadastroEmailDuplicadoException(
					MENSAGEM_ERRO_EMAIL_EXISTENTE);
		}

		objprep = conexao
				.getRetornarEstadoDePreparo(SQL_TESTE_CADASTRO_NOME_EXISTE);
		objprep.setString(1, u.getNomeUsuario());
		objrs = objprep.executeQuery();

		b = false;

		objrs.last();

		if (objrs.getRow() == 1) {
			b = true;
		}

		if (b) {
			throw new ErroCadastroNomeDuplicadoException(
					MENSAGEM_ERRO_NOME_EXISTENTE);
		}

		objprep = conexao.getRetornarEstadoDePreparo(SQL_INSERIR_USUARIO);
		objprep.setString(1, u.getNomeUsuario());
		objprep.setString(2, u.getEmail());
		objprep.setString(3, u.getSenha());

		objprep.executeUpdate();
	}

	/**
	 * Retorna uma lista contendo todos os usuários devidamente cadastrados na
	 * base de dados.
	 * 
	 * @return Lista de usuários cadastrados na base de dados.
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 * @throws SQLException
	 *             A SQL não está correta.
	 */
	public List<Usuario> getAll() throws SQLException, ErroDeConexaoException {
		List<Usuario> a = new ArrayList<>();

		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_RETORNAR_TUDO);

		ResultSet objrs = objprep.executeQuery();

		while (objrs.next()) {
			a.add(new Usuario(objrs.getString(1), objrs.getString(2), objrs
					.getString(3)));
		}

		return a;
	}

	/**
	 * Retorna o estado online do usuário, sendo ele um valor booleano.
	 * <b>Importante:</b> A inexistencia do usuário na base de dados retornará
	 * em uma exceção do tipo <b>SQLException</b> e deve ser tratada.
	 * 
	 * @param u
	 *            Usuário procurado.
	 * @return Retorna <b>TRUE</b> se o usuário foi previamente logado no
	 *         sistema.
	 * @throws SQLException
	 *             Caso o usuário não exista na base de dados ou a sql está
	 *             incorreta esta exceção é lançada.
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 */
	public boolean isOnline(Usuario u) throws SQLException,
			ErroDeConexaoException {
		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_ONLINE_POR_EMAIL);
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		objrs.next();
		boolean b = objrs.getBoolean(1);
		return b;
	}

	/**
	 * Muda o estado online do cliente no banco de dados caso o email e senha
	 * estejam corretos. É retornado uma consulta apenas com o email, e logo
	 * após, com email e senha para garantir que exista uma mensagem de erro
	 * diferente para quando o email ou senha estiver incorreto.
	 * 
	 * @param u
	 *            POJO contendo email e senha do usuário que quer fazer logon.
	 * @throws SQLException
	 *             A SQL não está correta.
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 * @throws LoginEmailIncorretoException
	 *             O email disponibilizado não está cadastrado na base de dados.
	 * @throws LoginSenhaIncorretaException
	 *             A senha disponibilizada não está correta.
	 */
	public void logar(Usuario u) throws SQLException, ErroDeConexaoException,
			LoginEmailIncorretoException, LoginSenhaIncorretaException {

		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_RETORNAR_POR_EMAIL);
		objprep.setString(1, u.getEmail());
		ResultSet rs = objprep.executeQuery();

		boolean emailExiste = false;

		rs.last();

		if (rs.getRow() == 1) {
			emailExiste = true;
		}

		if (!emailExiste) {
			throw new LoginEmailIncorretoException(
					MENSAGEM_ERRO_EMAIL_INEXISTENTE);
		}

		objprep = conexao.getRetornarEstadoDePreparo(SQL_LOGIN);
		objprep.setString(1, u.getEmail());
		objprep.setString(2, u.getSenha());
		rs = objprep.executeQuery();

		boolean senhaCorreta = false;

		rs.last();

		if (rs.getRow() == 1) {
			senhaCorreta = true;
		}

		if (!senhaCorreta) {
			throw new LoginSenhaIncorretaException(
					MENSAGEM_ERRO_SENHA_INCORRETA);
		}

		objprep = conexao.getRetornarEstadoDePreparo(SQL_SET_ONLINE);
		objprep.setString(1, u.getEmail());
		objprep.setString(2, u.getSenha());
		objprep.executeUpdate();
	}
	
	public void deslogar(Usuario u) throws SQLException, ErroDeConexaoException {
		PreparedStatement objprep = conexao.getRetornarEstadoDePreparo(SQL_SET_OFFLINE);
		objprep.setString(1, u.getEmail());
		objprep.setString(2, u.getSenha());
		objprep.executeUpdate();
	}
	/**
	 * Executa o comando <i>truncate</i> na tabela <b>usuarios</b> apagando
	 * todos os seus dados. Este método pode ser executado pelo cliente com o
	 * envio do código <i>reset</i> a qualquer momento.
	 * 
	 * @throws SQLException
	 *             Se ocorrer algum erro de sintaxe.
	 * @throws ErroDeConexaoException
	 *             A conexão com o banco de dados não está disponível.
	 */
	public synchronized void reset() throws SQLException, ErroDeConexaoException {
		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo("truncate table usuarios");
		objprep.executeUpdate();
	}

}
