package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * O recurso solicitado (Servlet, JSP) é sempre criado antes da execucao de
 * qualquer filtro (caso ainda nao tenha sido criado), ou seja, antes da chamada
 * ao metodo doFilter().
 * 
 * @author Matheus
 * 
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821121089141927458L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("myHead", "test");
		resp.getWriter().write("Servlet diz: MyServlet\n");
	}

}
