package sse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ServletEventSender", asyncSupported = true)
public class ServletEventSender extends HttpServlet {

	private static final long serialVersionUID = -3545078343706493379L;

	/**
	 * Server-Sent Event é um mecanismo executado em cima do protocolo HTTP que
	 * permite o recebimento de dados do servidor em um determinado periodo de
	 * tempo. Com esse mecanismo, o cliente realiza uma requisição ao servidor e
	 * ele responde com o cabecalho content-type=text/event-stream, isto
	 * significa que a resposta ainda não está completa, permitindo assim, o
	 * envio de dados para o cliente de forma continua.
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");

		PrintWriter writer = resp.getWriter();

		AsyncContext asyncContext = req.startAsync();
		asyncContext.start(() -> {
			try {
				while (true) {
					/*
					 * As mensagens enviadas para o cliente devem seguir um
					 * padrão, isto é, sempre começam com "data:" e devem ser
					 * finalizadas com dois escapes "\n\n".
					 */
					writer.write("data: Mensagem " + Math.random() * 100 + "\n\n");
					writer.flush();
					TimeUnit.SECONDS.sleep(2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
