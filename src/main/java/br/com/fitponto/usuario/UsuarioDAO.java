package br.com.fitponto.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitponto.connection.ConnectionFactory;
import br.com.fitponto.usuario.Usuario;

public class UsuarioDAO {
	public List<Usuario> listar() {
		//Requisição SQL
		String sql = "SELECT * FROM usuario";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("UsuarioId"));
				usuario.setNome(rs.getString("Nome"));
				usuario.setLogin(rs.getString("Login"));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setPerfil(rs.getInt("Perfil"));
				usuario.setChefeid(rs.getInt("ChefeId"));
				usuario.setCpf(rs.getString("CPF"));
				
				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	
	public List<Usuario> listaSupervisores() {
		//Requisição SQL
		String sql = "SELECT * FROM Usuario WHERE Perfil > 1";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("UsuarioId"));
				usuario.setNome(rs.getString("Nome"));
				
				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	
	
	public List<Usuario> listaHorasTrabalhadas() {
		//Requisição SQL
		String sql = "SELECT U.Nome AS NomeUsuario, CAST(TIME(SUM(CAST(A.Total AS TIME))) AS CHAR(20)) AS TotalHorasTrabalhadasFormatado, CONCAT(CAST(((SUM(CAST(A.Total AS TIME))/10000)/160) * 100 AS CHAR(6)),'%') AS TotalHorasTrabalhadasPerc FROM Apontamento AS A INNER JOIN Usuario AS U ON A.UsuarioID = U.UsuarioID WHERE A.Status = 'Aprovado' GROUP BY U.Nome";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("NomeUsuario"));
				usuario.setHorasTrabForm(rs.getString("TotalHorasTrabalhadasFormatado"));
				usuario.setHorasTrabPerc(rs.getString("TotalHorasTrabalhadasPerc"));
				
				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	
	public List<Usuario> listaAprovacoes() {
		//Requisição SQL
		String sql = "SELECT U.Nome AS NomeUsuario, COUNT(CASE WHEN A.Status = 'Aprovado' THEN A.ApontamentoID ELSE NULL END) AS NumeroAprovados, CONCAT(CAST((COUNT(CASE WHEN A.Status = 'Aprovado' THEN A.ApontamentoID ELSE NULL END)/COUNT(A.ApontamentoID))*100 AS CHAR(7)),'%') AS PercAprovados, COUNT(CASE WHEN A.Status = 'Reprovado' THEN A.ApontamentoID ELSE NULL END) AS NumeroReprovados, CONCAT(CAST((COUNT(CASE WHEN A.Status = 'Reprovado' THEN A.ApontamentoID ELSE NULL END)/COUNT(A.ApontamentoID)) * 100 AS CHAR(7)),'%') AS PercReprovados, COUNT(CASE WHEN A.Status = 'Pendente' THEN A.ApontamentoID ELSE NULL END) AS NumeroPendentes, CONCAT(CAST((COUNT(CASE WHEN A.Status = 'Pendente' THEN A.ApontamentoID ELSE NULL END)/COUNT(A.ApontamentoID)) * 100 AS CHAR(7)),'%') AS PercPendentes FROM Apontamento AS A INNER JOIN Usuario AS U ON A.UsuarioID = U.UsuarioID GROUP BY U.Nome";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("NomeUsuario"));
				usuario.setNumAprovados(rs.getInt("NumeroAprovados"));
				usuario.setPercAprovados(rs.getString("PercAprovados"));
				usuario.setNumReprovados(rs.getInt("NumeroReprovados"));
				usuario.setPercReprovados(rs.getString("PercReprovados"));
				usuario.setNumPendentes(rs.getInt("NumeroPendentes"));
				usuario.setPercPendentes(rs.getString("PercPendentes"));
				
				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	

	public void atualizar(Usuario usuario) {
		//Requisição SQL
		String sql = "UPDATE usuario SET login = ?, nome = ?, cargo = ?, perfil = ?, chefeid = ?, cpf =  ? WHERE usuarioid = ?";

		PreparedStatement ps;

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getCargo());
			ps.setInt(4, usuario.getPerfil());
			ps.setInt(5, usuario.getChefeid());
			ps.setString(6, usuario.getCpf());
			ps.setInt(7, usuario.getId());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deletar(Usuario usuario) {
		//Requisição SQL
		String sql = "DELETE FROM usuario WHERE usuarioid = ?";

		PreparedStatement ps;

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, usuario.getId());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserir(Usuario usuario) {
		//Requisição SQL
		String sql = "INSERT INTO usuario(login,senha,nome,cargo,perfil,chefeid,cpf) VALUES(?,?,?,?,?,?,?)";

		PreparedStatement ps;

		//Tenta realizar uma conexão com o banco de dados
		try (Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
			ps.setString(4, usuario.getCargo());
			ps.setInt(5, usuario.getPerfil());
			ps.setInt(6, usuario.getChefeid());
			ps.setString(7, usuario.getCpf());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
