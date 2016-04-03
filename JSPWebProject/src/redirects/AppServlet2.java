package redirects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppServlet2")
public class AppServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8668603249883780855L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Neste ponto a resposta ainda nao esta commitada, apenas quando o
		 * fluxo retornar para a AppServlet que a resposta sera commitada.
		 */
		resp.getWriter().println(super.getServletName());
	}

}
