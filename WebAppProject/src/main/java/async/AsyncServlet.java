package async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971549866955112699L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(req.isAsyncStarted());
		resp.getWriter().println(req.isAsyncSupported());

		AsyncContext ac = req.startAsync();
		ac.setTimeout(30000);
		ac.dispatch("/AsyncServlet2");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final PrintWriter pw = resp.getWriter();

		final AsyncContext ac = req.startAsync();
		ac.setTimeout(1000);
		ac.addListener(ac.createListener(AsyncContextListener.class));

		ac.start(new Runnable() {

			@Override
			public void run() {
				try {
					pw.println("Inside run");
					Thread.sleep(3000);
					// ac.complete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
