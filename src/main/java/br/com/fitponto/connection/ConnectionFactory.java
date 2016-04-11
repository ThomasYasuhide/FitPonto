package br.com.fitponto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//Dados do banco de dados
	private static final String host = "localhost";
	private static final String db = "fitponto";
	private static final String user = "root";
	private static final String password = "";
	
	public static Connection getConnection(){
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}
	
}