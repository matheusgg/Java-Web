package async;

import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncTask implements Runnable {

	private AsyncContext asyncContext;

	public AsyncTask(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}

	@Override
	public void run() {
		try {
			HttpServletRequest request = (HttpServletRequest) this.asyncContext.getRequest();
			HttpServletResponse response = (HttpServletResponse) this.asyncContext.getResponse();

			String msg = request.getParameter("msg");
			System.out.println("Mensagem recebida: " + msg);
			response.getWriter().println(new Date() + ": " + msg);
			this.asyncContext.complete();

		} catch (Exception e) {
			this.asyncContext.getRequest().getServletContext().log(e.getMessage(), e);
		}
	}

}
