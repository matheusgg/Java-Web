

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import controler.ControladorDeDados;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorDeDados control = new ControladorDeDados();
		Usuario u = new Usuario();
		u.setLogin(request.getParameter("user"));
		u.setPassword(request.getParameter("pass"));
		try{
			u = control.verificarUser(u);
		}catch(SQLException ex){
			ex.printStackTrace();
		}		
		
		if(!u.getLogin().equals("")){			
			request.setAttribute("usuarioLogado", u.getLogin());
			request.getRequestDispatcher("Page1.jsp").forward(request, response);
			System.out.println("Logado!");
		}else{			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
