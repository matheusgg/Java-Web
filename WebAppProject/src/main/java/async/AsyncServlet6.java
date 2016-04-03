package async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AsyncServlet6")
public class AsyncServlet6 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -425461656382674446L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(super.getServletName());

		if (req.getParameter("error") != null) {
			throw new ServletException(new IllegalAccessException("Erro acesso assincrono!"));
		}
	}
}
