package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import ws.ISumWS;

@WebServlet(value = "/WSTestServlet", loadOnStartup = 1)
public class WSTestServlet extends HttpServlet {

	private static final long serialVersionUID = 7905895886272779821L;

	/**
	 * A anotacao @WebServiceRef serve para indicar uma referencia a um
	 * webservice. Pode ser aplicada para atributos do tipo javax.xml.ws.Service
	 * ou endpoints que serao produzidos por um service. Neste caso, a classe
	 * ServiceProvider, que é um webservice client, será responsavel por criar
	 * as referencias de servicos que possuem uma porta do tipo ISumWS.
	 */
	@WebServiceRef(value = ServiceProvider.class)
	private ISumWS sumService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		resp.getWriter().write(this.sumService.sum(10, 10));
	}

}
