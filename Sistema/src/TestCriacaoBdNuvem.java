import java.sql.ResultSet;

import javax.naming.*;
import javax.sql.*;

import java.sql.*;


public class TestCriacaoBdNuvem {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_servcomunicacao",
		login = "b9810182bba4eb",
		senha = "d1edb77d";
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(url);
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		conn.close();
//		PreparedStatement ps = (PreparedStatement) con.prepareStatement("CREATE TABLE pessoas (id integer, nome varchar(100), PRIMARY KEY(id))");
//		ps.executeUpdate();
//		System.out.println("TABELA CRIADA!");
		
//		PreparedStatement ps2 = (PreparedStatement) con.prepareStatement("INSERT INTO pessoas values (?,?)");
//		ps2.setInt(1, 2);
//		ps2.setString(2, "Maurício");
//		ps2.execute();
//		System.out.println("PESSOA INSERIDA!");
		
		ResultSet rst = stmt.executeQuery("SELECT * FROM pessoas");

		while (rst.next()) {
			System.out.println("1: " + rst.getInt(1) + " - 2: " + rst.getString(2));
		}
		
		conn.close();
	}
}
