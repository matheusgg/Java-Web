package servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import async.AsyncTask;

@WebServlet(value = "/AsynServlet", asyncSupported = true)
public class AsynServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2342506516985993541L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AsyncContext context = req.startAsync();
		context.setTimeout(0);
		context.start(new AsyncTask(context));
	}

}
