package async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AsyncServlet4", asyncSupported = true)
public class AsyncServlet4 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971549866955112699L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final PrintWriter pw = resp.getWriter();
		final AsyncContext ac = req.startAsync();

		ac.start(new Runnable() {

			@Override
			public void run() {
				ac.dispatch("/AsyncServlet3");
				pw.println("end");
			}
		});
	}

}
