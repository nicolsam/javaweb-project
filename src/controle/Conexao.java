package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private Connection con = null;
	
	public Conexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "root";
			String password = "150b";
			String db = "aluno";
			String server = "jdbc:mysql://127.0.0.1:3306/" + db;
			
			this.con = DriverManager.getConnection(server, user, password);
			
			System.out.println("[-] Sucesso ao conectar com o banco de dados.");
		} catch(ClassNotFoundException e) {
			System.out.println("[-] Verifique a biblioteca JDBC.");
		} catch(SQLException e) {
			System.out.println("[-] Erro ao conectar no banco: " + e.getMessage());
		} catch(Exception e) {
			System.out.println("[-] Erro geral: " + e.getMessage());
		}
	}
	
	public Connection getCon() {
		return this.con;
	}
	
	public void fecharConexao() {
		try {
			this.con.close();
		} catch(SQLException e) {
			System.out.println("[-] Falha ao fechar a conexão.");
		}
	}
}
