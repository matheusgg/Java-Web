package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppServlet")
@ServletSecurity(value = @HttpConstraint(ServletSecurity.EmptyRoleSemantic.DENY), httpMethodConstraints = {
		@HttpMethodConstraint(value = "POST", rolesAllowed = "admin"), @HttpMethodConstraint(value = "GET", rolesAllowed = "user") })
public class AppServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("result", Math.random());

		RequestDispatcher rd = req.getRequestDispatcher("/admin/page1.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("result", Math.random());

		RequestDispatcher rd = req.getRequestDispatcher("/admin/page1.jsp");
		rd.forward(req, resp);
	}

}
