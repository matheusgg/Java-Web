package br.prime.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.prime.model.Usuario;

public class UsuarioDAO {
	private Connection con;
	
	public boolean cadastraComment(Usuario user){	
		String query = "INSERT INTO faleconosco (nome, email, intencao, anuncios, mensagem) VALUES (?,?,?,?,?);";
		con = ConectaBanco.getConnection();
		try{
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, user.getNome());
			stm.setString(2, user.getEmail());
			stm.setInt(3, user.getTipoComment());
			stm.setString(4, user.getRecebeAnuncio());
			stm.setString(5, user.getMensagem());			
			stm.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Erro ao cadastrar Comentário!" + ex.getMessage());
			return false;
		}
	}
	
	public ResultSet consultaProdutos(){
		String query = "SELECT * FROM produtos;";
		con = ConectaBanco.getConnection();
		try{
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			return rs;
		}catch(SQLException ex){
			System.out.println("Erro ao consultar o banco!" + ex.getMessage());
			return null;			
		}
	}
	
	public boolean verificaLogin(Usuario user){
		boolean verUserExiste = false;
		String query = "SELECT * FROM login;";
		con = ConectaBanco.getConnection();
		try{
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				if(rs.getString("usuario").equals(user.getUser()) && rs.getString("senha").equals(user.getPassword())){
					verUserExiste = true;
					break;
				}				
			}
			return verUserExiste;			
		}catch(SQLException ex){
			System.out.println("Erro ao consultar o banco!" + ex.getMessage());
			return verUserExiste;		
		}	
	}
}
