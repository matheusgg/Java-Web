package servletContext;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ContextServlet", loadOnStartup = 2)
public class ContextServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 368053390259758551L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = super.getServletContext();

		List<String> libs = (List<String>) sc.getAttribute(ServletContext.ORDERED_LIBS);
		resp.getWriter().println(libs);
	}

}
