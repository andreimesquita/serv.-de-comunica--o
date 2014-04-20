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
 * História de usuário sendo implementada HU1C01, C02, C03, C04
 * 
 * nomeUsuario / email / senha / online
 * 
 * @version 2
 * @author Maurício / Ândrei
 */
public class UsuarioDAO {

	private static final String SQL_CRIAR_TABELA_USUARIO = "create table usuarios ("
			+ "nome varchar(50) primary key,"
			+ "email varchar(50),"
			+ "senha varchar(16)" + ");";

	private static final String SQL_ONLINE_POR_EMAIL = "select online from usuarios where email = ?";
	private static final String SQL_SET_ONLINE = "update usuarios set online = true where email = ? AND senha = ?";
	private static final String SQL_LOGIN = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
	private static final String SQL_INSERIR_USUARIO = "insert into usuarios (nome,email,senha) values (?,?,?)";
	private static final String SQL_RETORNAR_POR_EMAIL = "select * from usuarios where email=?";
	private static final String SQL_RETORNAR_TUDO = "select * from usuarios";
	private static final String SQL_TESTE_CADASTRO_NOME_EXISTE = "select * from usuarios where nome=?";
	private static final String MENSAGEM_ERRO_EMAIL_EXISTENTE = "O e-mail selecionado já está em uso.";
	private static final String MENSAGEM_ERRO_NOME_EXISTENTE = "O usuário selecionado já está em uso.";
	private static final String MENSAGEM_ERRO_SENHA_INCORRETA = "";
	private static final String MENSAGEM_ERRO_EMAIL_INEXISTENTE = "";
	private static final String TABELA_USUARIOS_NOME = "usuarios";

	private Conexao conexao;

	public UsuarioDAO() throws SQLException, ErroDeConexaoException {
		this.conexao = Conexao.getInstance();
		this.conexao.conectar();
		// this.conexao.getRetornarEstadoDePreparo(SQL_CRIAR_TABELA_USUARIO);
	}

	public void cadastrar(Usuario u)
			throws ErroCadastroEmailDuplicadoException,
			ErroCadastroNomeDuplicadoException, ErroDeConexaoException,
			SQLException {

		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_RETORNAR_POR_EMAIL);
		objprep.setString(1, u.getEmail());
		ResultSet objrs = objprep.executeQuery();
		boolean b = false;

		while (objrs.next()) {
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
		while (objrs.next()) {
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

	public List<Usuario> getAll() throws Exception {
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

	public void getByEmail() {

	}

	public void logar(Usuario u) throws SQLException, ErroDeConexaoException,
			LoginEmailIncorretoException, LoginSenhaIncorretaException {

		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo(SQL_RETORNAR_POR_EMAIL);
		objprep.setString(1, u.getEmail());
		ResultSet rs = objprep.executeQuery();

		boolean emailExiste = false;

		while (rs.next()) {
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

		while (rs.next()) {
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

	public void reset() throws SQLException, ErroDeConexaoException {
		PreparedStatement objprep = conexao
				.getRetornarEstadoDePreparo("truncate table usuarios");
		objprep.executeUpdate();
	}

	/**
	 * Teste
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("DAO");
		UsuarioDAO dao = new UsuarioDAO();
		dao.reset();

		Usuario u = new Usuario("andrei", "1", "1");

		dao.cadastrar(u);

		for (Usuario us : dao.getAll()) {
			System.out.println("Nome: " + us.getNomeUsuario());
		}

		System.out.println("Deve ser false! = " + dao.isOnline(u));

		dao.logar(u);

		System.out.println("Deve ser true! = " + dao.isOnline(u));

		System.exit(0);
	}

}
