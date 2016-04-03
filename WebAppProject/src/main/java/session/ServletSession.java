package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletSession")
public class ServletSession extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8462470766931297371L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		resp.getWriter().println(session.getId());
		resp.getWriter().println(session.isNew());
		resp.getWriter().println(session.getLastAccessedTime());
		resp.getWriter().println(session.getCreationTime());

		if (req.getParameter("add") != null) {
			session.setAttribute("attrTeste", new AppSessionAttributeListener());
		}

		if (req.getParameter("remove") != null) {
			session.removeAttribute("attrTeste");
		}

		if (req.getParameter("replace") != null) {
			session.setAttribute("attrTeste", new AppSessionAttributeListener());
		}
	}

}
