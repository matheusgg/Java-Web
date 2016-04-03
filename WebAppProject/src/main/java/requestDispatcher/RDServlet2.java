package requestDispatcher;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rd2")
public class RDServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733994815911275202L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.getWriter().println(session.getId());

		if (req.getDispatcherType() == DispatcherType.FORWARD) {
			resp.setHeader("metodoDeRedirecionamento", DispatcherType.FORWARD.name());

			resp.getWriter().println(req.getAttribute(RequestDispatcher.FORWARD_CONTEXT_PATH));// javax.servlet.forward.context_path
			resp.getWriter().println(req.getAttribute(RequestDispatcher.FORWARD_PATH_INFO));// javax.servlet.forward.path_info
			resp.getWriter().println(req.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING));// javax.servlet.forward.query_string
			resp.getWriter().println(req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));// javax.servlet.forward.request_uri
			resp.getWriter().println(req.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH));// javax.servlet.forward.servlet_path

		} else {
			resp.setHeader("metodoDeRedirecionamento", DispatcherType.INCLUDE.name());
			resp.addCookie(new Cookie("cookieTeste", "valor"));

			resp.getWriter().println(req.getAttribute(RequestDispatcher.INCLUDE_CONTEXT_PATH));// javax.servlet.include.context_path
			resp.getWriter().println(req.getAttribute(RequestDispatcher.INCLUDE_PATH_INFO));// javax.servlet.include.path_info
			resp.getWriter().println(req.getAttribute(RequestDispatcher.INCLUDE_QUERY_STRING));// javax.servlet.include.query_string
			resp.getWriter().println(req.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI));// javax.servlet.include.request_uri
			resp.getWriter().println(req.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH));// javax.servlet.include.servlet_path
		}
	}

}
