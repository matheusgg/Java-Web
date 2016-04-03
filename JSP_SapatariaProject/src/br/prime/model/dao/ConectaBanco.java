package br.prime.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {
	private static Connection con;
	private static String user = "root";
	private static String pass = "root";
	private static String url = "jdbc:mysql://localhost/sapataria";
	
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Registrado!");
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("Conectado!");
			return con;					
		}catch(ClassNotFoundException ex){
			System.err.println("Driver Não Registrado!" + ex.getMessage());			
		}catch(SQLException ex){
			System.err.println("Não Conectado!" + ex.getMessage());			
		}
		return null;
	}
}
