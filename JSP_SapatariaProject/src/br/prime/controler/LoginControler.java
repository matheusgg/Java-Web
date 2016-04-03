package br.prime.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.prime.model.Usuario;
import br.prime.model.dao.ConectaBanco;
import br.prime.model.dao.UsuarioDAO;

/**
 * Servlet implementation class LoginControler
 */
@WebServlet("/LoginControler")
public class LoginControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginControler() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = new Usuario();
		user.setUser(request.getParameter("login"));
		user.setPassword(request.getParameter("pass"));
		UsuarioDAO userControler = new UsuarioDAO();
		boolean ver = userControler.verificaLogin(user);
		try{
			ConectaBanco.getConnection().close();
			System.out.println("Conexão Encerrada!");
		}catch(SQLException ex){
			System.out.println("Conexão não encerrada!");
		}	
		if(ver == true){
			request.setAttribute("user", user.getUser());
			request.getRequestDispatcher("menus.jsp").forward(request, response);
		}else{
			request.setAttribute("naoCadastrado", "Usuário não cadastrado ou senha incorreta!");
			request.getRequestDispatcher("menus.jsp").forward(request, response);
		}
	}
}
