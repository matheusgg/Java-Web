package servlets;

import java.io.IOException;

import javax.annotation.security.DeclareRoles;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Roles tambem podem ser declaradas por meio da anotacao DeclareRoles. Esta
 * anotacao so pode ser aplicada para classes que implementem
 * javax.servlet.Servlet. Aqui e possivel especificar todas as roles que a
 * aplicacao possui.
 * 
 * @author Matheus
 * 
 */
@DeclareRoles({ "simple" })
/**
 * A anotacao ServletSecurity e utilizada para impor restricoes de seguranca para determinada servlet. 
 * E uma segunda opcao ao web.xml. HttpMethodConstraints define configuracoes para metodos
 * HTTP especificos, caso um comportamento mais generico seja requerido, HttpConstraint deve ser utilizado
 * (padrao). emptyRoleSemantic e utilizado quando rolesAllowed nao e especificada, uma vez que define um comportamento
 * padrao para as requisicoes especificadas.
 * 
 * @author Matheus
 *
 */
@ServletSecurity(value = @HttpConstraint(ServletSecurity.EmptyRoleSemantic.DENY), httpMethodConstraints = { @HttpMethodConstraint(value = "GET") })
@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("restrito/page.jsp");
		rd.forward(req, resp);
	}

}
