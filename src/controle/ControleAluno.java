package controle;
import modelo.Aluno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class ControleAluno {
	public boolean inserir(Aluno al) {
		boolean resultado = false;
		Conexao con = new Conexao();
		try {
			
			String comando = "INSERT INTO alunos(Numero, Nome, Idade, Hobby, Sexo) VALUES(?,?,?,?,?);";
			PreparedStatement ps = con.getCon().prepareStatement(comando);
			
			ps.setInt(1, al.getNumero());
			ps.setString(2, al.getNome());
			String data = al.dataNascimento.getYear() + "-" + al.dataNascimento.getMonth() + "-" + al.dataNascimento.getDate();
			Date dataSQL = Date.valueOf(data);
			ps.setDate(3, dataSQL);
			ps.setString(4, al.getHobby());
			ps.setString(5, al.getSexo());
			
			if(!ps.execute()) {
				resultado = true;
			}
		} catch(SQLException e) {
			 System.out.println("[-] Erro ao inserir: " + e.getMessage());
		} finally {
			con.fecharConexao();
		}
		return resultado;
	}
	
	
	public boolean deletar(Aluno al) {
		boolean resultado = false;
		Conexao con = new Conexao();
		
		try {
			String comando = "DELETE FROM alunos WHERE Numero = ?";
			PreparedStatement ps = con.getCon().prepareStatement(comando);
			
			ps.setInt(1, al.getNumero());
			
			System.out.println("[+] Registro deletado com sucesso.");
			if(!ps.execute()) {
				resultado = true;
			}
				
		} catch(SQLException e) {
			System.out.println("[-] Erro no banco de dados");
		}
		return resultado;
	}
	
	public boolean atualizar(Aluno al) {
		boolean resultado = false;
		Conexao con = new Conexao();
		
		try {
			String comando = "UPDATE alunos SET Nome = ?, Idade = ?, Hobby = ?, Sexo = ? WHERE Numero = ?";
			PreparedStatement ps = con.getCon().prepareStatement(comando);
		
			ps.setString(1, al.getNome());
			String data = al.dataNascimento.getYear() + "-" + al.dataNascimento.getMonth() + "-" + al.dataNascimento.getDate();
			Date dataSQL = Date.valueOf(data);
			ps.setDate(2, dataSQL);
			ps.setString(3, al.getHobby());
			ps.setString(4, al.getSexo());
			ps.setInt(5, al.getNumero());
			
			System.out.println("[+] Registro atualizado com sucesso.");
			
			if(!ps.execute()) {
				resultado = true;
			} 
			
		} catch(SQLException e) {
			System.out.println("[-] Erro no banco de dados: " + e.getMessage());
		}
		return resultado;
	}
}
