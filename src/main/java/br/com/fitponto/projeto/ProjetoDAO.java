package br.com.fitponto.projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitponto.projeto.Projeto;
import br.com.fitponto.connection.ConnectionFactory;

public class ProjetoDAO {
	
	public List<Projeto> listar(){
		//Requisição SQL
		String sql = "SELECT * FROM projeto";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Projeto> lista = new ArrayList<Projeto>();
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("ProjetoId"));
				projeto.setNome(rs.getString("Nome"));
				
				lista.add(projeto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	

	public void atualizar(Projeto projeto){
		//Requisição SQL
		String sql = "UPDATE projeto SET nome = ? WHERE projetoid = ?";
		
		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, projeto.getNome());
			ps.setInt(2, projeto.getId());
			
			ps.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void deletar(Projeto projeto){
		//Requisição SQL
		String sql = "DELETE FROM projeto WHERE projetoid = ?";
		
		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, projeto.getId());
			
			ps.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void inserir(Projeto projeto) {
		//Requisição SQL
		String sql = "INSERT INTO projeto(nome) VALUES(?)";
		
		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, projeto.getNome());
			
			ps.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
