package br.com.fitponto.tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitponto.tarefa.Tarefa;
import br.com.fitponto.connection.ConnectionFactory;

public class TarefaDAO {
	public List<Tarefa> listar() {
		//Requisição SQL
		String sql = "SELECT T.*,P.Nome AS ProjetoNome FROM tarefa AS T INNER JOIN Projeto AS P ON T.ProjetoID = P.ProjetoID";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Tarefa> lista = new ArrayList<Tarefa>();

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("TarefaId"));
				tarefa.setNome(rs.getString("Nome"));
				tarefa.setProjetoid(rs.getInt("ProjetoId"));
				tarefa.setNomeProjeto(rs.getString("ProjetoNome"));

				lista.add(tarefa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void atualizar(Tarefa tarefa) {
		//Requisição SQL
		String sql = "UPDATE tarefa SET nome = ?, projetoid = ? WHERE tarefaid = ?";

		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setString(1, tarefa.getNome());
			ps.setInt(2, tarefa.getProjetoid());
			ps.setInt(3, tarefa.getId());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deletar(Tarefa tarefa) {
		//Requisição SQL
		String sql = "DELETE FROM tarefa WHERE tarefaid = ?";

		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tarefa.getId());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserir(Tarefa tarefa) {
		//Requisição SQL
		String sql = "INSERT INTO tarefa(nome,projetoid) VALUES(?,?)";

		PreparedStatement ps;

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tarefa.getNome());
			ps.setInt(2, tarefa.getProjetoid());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
