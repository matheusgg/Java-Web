package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;
import model.dao.ConectaBanco;

public class ControladorDeDados {
	public Usuario verificarUser(Usuario usuario) throws SQLException{
		Usuario u = new Usuario();
		String query = "SELECT * FROM usuarios";
		Connection con = ConectaBanco.getConection();
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			String user = rs.getString("usuario");
			String password = rs.getString("pass");
			if (usuario.getLogin().equals(user) && usuario.getPassword().equals(password)) {
				u.setLogin(user);
				u.setPassword(password);
				con.close();
				return u;
			}
		}
		con.close();
		u.setLogin("");
		u.setPassword("");
		return u;			
	}
}
