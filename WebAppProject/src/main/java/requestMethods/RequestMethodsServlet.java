package requestMethods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestMethodsServlet")
public class RequestMethodsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1502435281326989520L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(req.getAuthType());
		resp.getWriter().println(req.getCharacterEncoding());
		resp.getWriter().println(req.getContentType());
		resp.getWriter().println(req.getContextPath());
		resp.getWriter().println(req.getRequestURI());
		resp.getWriter().println(req.getRequestURL());
		resp.getWriter().println(req.getPathInfo());
		resp.getWriter().println(req.getQueryString());
		resp.getWriter().println(req.getMethod());
		resp.getWriter().println(req.getServletPath());
	}
	
	

}
