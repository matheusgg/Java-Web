package async;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AsyncServlet5", asyncSupported = true)
public class AsyncServlet5 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1073749131925661375L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AsyncContext ac = req.startAsync();
		ac.setTimeout(-1);
		ac.addListener(new AsyncContextListener());
		ac.dispatch("/AsyncServlet6?error=true");
	}

}
