package requestDispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rd1")
public class RDServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733994815911275202L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Before forward or include");

		String redirectTo = req.getParameter("redirectTo");
		String method = req.getParameter("method");
		String rdType = req.getParameter("rdType");

		if (redirectTo != null) {

			RequestDispatcher rd = null;

			if ("named".equals(rdType)) {
				rd = super.getServletContext().getNamedDispatcher("requestDispatcher.RDServlet2");
			} else {
				rd = req.getRequestDispatcher("rd2");
			}

			if ("forward".equals(method)) {
				rd.forward(req, resp);
			} else {
				rd.include(req, resp);
			}

		}
		
		resp.getWriter().println("After forward or include");
		resp.flushBuffer();
	}

}
