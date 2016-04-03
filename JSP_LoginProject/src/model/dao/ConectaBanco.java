package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {
	private static Connection con;
	
	public static Connection getConection() throws SQLException{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver Registrado!!");
		}catch(ClassNotFoundException ex){
			System.out.println("Driver Não Registrado!!");
		}		
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Usuarios;integratedSecurity=true");
		return con;
	}
}
