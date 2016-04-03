package dynamic;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DynamicServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484688398351463174L;

	@Resource(name = "java:comp/env/jdbc/app_db", shareable = true, mappedName = "ap_db")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<h1>Parametros de inicializacao</h1>");
		resp.getWriter().println("<br />");

		Enumeration<String> initParameterNames = super.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String parameterName = initParameterNames.nextElement();
			resp.getWriter().print(parameterName);
			resp.getWriter().print(": ");
			resp.getWriter().println(super.getInitParameter(parameterName));
			resp.getWriter().println("<br />");
		}

		resp.getWriter().println(this.ds);
	}

}
