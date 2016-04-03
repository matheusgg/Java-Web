package redirects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		writer.println("before forwarding");

		/*
		 * O metodo forward de RequestDispatcher e o metodo sendRedirect de
		 * HttpServletResponse, limpam o buffer e fazem o redirecionamento. A
		 * resposta é commitada depois que o fluxo retorna para a servlet que
		 * fez o redirecionamento. Qualquer tentativa de enviar mais dados para
		 * a resposta dentro dessa servlet sera ignorada.
		 */
		RequestDispatcher rd = req.getRequestDispatcher("/AppServlet2");
		rd.forward(req, resp);

		// resp.sendRedirect("AppServlet2");

		/*
		 * Isso sera ignorado, pois a resposta ja esta commitada.
		 */
		writer.println("after forwarding");
	}

}
