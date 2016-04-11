package br.com.fitponto.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fitponto.login.User;
import br.com.fitponto.connection.ConnectionFactory;

public class LoginDAO {
	
	public User doLogin(User user){
		//Query SQL
		String sql = "SELECT * FROM usuario WHERE Login = ? AND Senha = ?";
		ResultSet rs;
		
		//Tenta realizar uma conexão com o banco de dados
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				user.setId(Integer.parseInt(rs.getString("UsuarioID")));
				user.setNome(rs.getString("Nome"));
				user.setPosition(rs.getString("Cargo"));
				user.setPerfil(Integer.parseInt(rs.getString("Perfil")));
			} //End if
			
			ps.close();
			conn.close();
			
			return user;
			
		} catch (SQLException e) {
			System.out.println("Houve algum problema na sua requisição com o banco de dados");
		} //END try-catch
		
		return user;
	} //END doLogin

}
