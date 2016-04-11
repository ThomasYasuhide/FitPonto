package br.com.fitponto.apontamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.fitponto.connection.ConnectionFactory;

public class ApontamentoDAO {
	

	
	public void inserir(Apontamento apontamento) {
		//Requisição SQL
		String sql = "INSERT INTO apontamento (UsuarioID, TarefaID, Inicio, Fim, Total, Observacao, Status) VALUES(?,?,?,?,calculadiferenca(?,?),?,?)";

		PreparedStatement ps;

		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, apontamento.getUserID());
			ps.setInt(2, apontamento.getTarefaID());
			ps.setTimestamp(3, Timestamp.valueOf(apontamento.getInicio())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(4, Timestamp.valueOf(apontamento.getFim())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(5, Timestamp.valueOf(apontamento.getFim())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(6, Timestamp.valueOf(apontamento.getInicio())); //Transforma LocalDateTime para Timestamp
			ps.setString(7, apontamento.getObs());
			ps.setString(8, "Pendente");  //Todo apontamento criado entra como pendente automatico
			
			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void atualizar(Apontamento apontamento){
		//Requisição SQL
		String sql = "UPDATE apontamento SET TarefaID = ?, inicio = ?, fim = ?, total = calculadiferenca(?,?), Observacao = ?, Status = ? WHERE apontamentoID = ?";
		
		PreparedStatement ps;
		
		System.out.println(Timestamp.valueOf(apontamento.getFim()));
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, apontamento.getTarefaID());
			ps.setTimestamp(2, Timestamp.valueOf(apontamento.getInicio())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(3, Timestamp.valueOf(apontamento.getFim())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(4, Timestamp.valueOf(apontamento.getFim())); //Transforma LocalDateTime para Timestamp
			ps.setTimestamp(5, Timestamp.valueOf(apontamento.getInicio())); //Transforma LocalDateTime para Timestamp
			ps.setString(6, apontamento.getObs());
			ps.setString(7, "Pendente");
			ps.setInt(8, apontamento.getId());
			

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(Apontamento apontamento){
		//Requisição SQL
		String sql = "DELETE FROM apontamento WHERE apontamentoID = ?";

		PreparedStatement ps;
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, apontamento.getId());

			ps.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public List<Apontamento> listar(HttpSession session){
		//Requisição SQL
		String sql = "SELECT A.apontamentoID, A.usuarioid, U.Nome AS nomeUsuario, A.tarefaid, T.Nome AS nomeTarefa, A.inicio, A.fim, A.total, A.observacao, A.status FROM apontamento AS A INNER JOIN usuario AS U ON A.UsuarioID = U.UsuarioID INNER JOIN tarefa AS T ON A.tarefaID = T.TarefaID WHERE A.Status != 'Aprovado' AND U.UsuarioID = "+ session.getAttribute("sessionUserID");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Apontamento> lista = new ArrayList<Apontamento>();
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//Varrer os apontamentos e colocar dentro da array lista
			while(rs.next()){
				Apontamento apontamento = new Apontamento();
				apontamento.setId(rs.getInt("apontamentoID"));
				apontamento.setUserID(rs.getInt("usuarioID"));
				apontamento.setTarefaID(rs.getInt("tarefaID"));
				apontamento.setTarefa(rs.getString("nomeTarefa"));
				apontamento.setInicio(rs.getTimestamp("inicio").toLocalDateTime()); //Transforma TimeStamp em LocalDateTime
				apontamento.setFim(rs.getTimestamp("fim").toLocalDateTime()); //Transforma TimeStamp em LocalDateTime
				apontamento.setHoraTotal(rs.getString("total"));
				apontamento.setObs(rs.getString("observacao"));
				apontamento.setStatus(rs.getString("status"));
				lista.add(apontamento);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Apontamento> listarPendentes(HttpSession session){
		//Requisição SQL
		String sql = "SELECT A.apontamentoID, A.usuarioid, U.Nome AS nomeUsuario, A.tarefaid, T.Nome AS nomeTarefa, A.inicio, A.fim, A.total, A.observacao, A.status FROM apontamento AS A INNER JOIN usuario AS U ON A.UsuarioID = U.UsuarioID INNER JOIN tarefa AS T ON A.tarefaID = T.TarefaID WHERE U.chefeid = "+session.getAttribute("sessionUserID")+" AND A.Status = 'Pendente'";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Apontamento> lista = new ArrayList<Apontamento>();
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//Varrer os apontamentos e colocar dentro da array lista
			while(rs.next()){
				Apontamento apontamento = new Apontamento();
				apontamento.setId(rs.getInt("apontamentoID"));
				apontamento.setUserID(rs.getInt("usuarioid"));
				apontamento.setUser(rs.getString("nomeUsuario"));
				apontamento.setTarefaID(rs.getInt("tarefaid"));
				apontamento.setTarefa(rs.getString("nomeTarefa"));
				apontamento.setInicio(rs.getTimestamp("inicio").toLocalDateTime()); //Transforma TimeStamp em LocalDateTime
				apontamento.setFim(rs.getTimestamp("fim").toLocalDateTime()); //Transforma TimeStamp em LocalDateTime
				apontamento.setHoraTotal(rs.getString("total"));
				apontamento.setObs(rs.getString("observacao"));
				apontamento.setStatus(rs.getString("status"));
				lista.add(apontamento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	
	public void aprovarPendentes(Apontamento apontamento){
		//Requisição SQL
		String sql = "UPDATE apontamento SET Status = ? WHERE apontamentoID = ?";
	
		PreparedStatement ps;
	
		System.out.println(apontamento.getInicio());
	
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, apontamento.getStatus());
			ps.setInt(2, apontamento.getId());
	
			ps.execute();
	
			conn.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
