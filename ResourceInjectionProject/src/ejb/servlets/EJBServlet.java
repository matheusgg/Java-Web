package ejb.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ejb.AppBean;
import ejb.AppBean2;

/**
 * Assim com Resources, é possivel especificar varios mapeamentos a referencias
 * EJB utilizando a anotacao @EJBs, porem da mesma forma como acontece com
 * Resources, o lookup e a injecao devem ser feitos manualmente pelo
 * programador.
 * 
 * @author Matheus
 * 
 */
// @EJBs({ @EJB(beanName = "AppBean") })
@WebServlet("/EJBServlet")
public class EJBServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4329758634349119027L;

	// Jboss
	@Resource(name = "java:jboss/datasources/MySQL")
	private DataSource dataSource;

	// private AppBean appBean;

	/*
	 * É possivel especificar EJB's atraves de anotacoes ao inves da
	 * configuracao pelo web.xml. "beanName" é o nome do EJB configurado no
	 * atributo "name" das anotacoes Stateless ou Stateful, caso o atributo
	 * "name" nao seja especificado em Stateless ou Stateful, o nome simples da
	 * classe é utilizado por padrao, ex.: "AppBean". O atributo "name"
	 * representa o nome logico do EJB que sera utilizado como referencia, ex.:
	 * java:module/AppBean. "beanInterface" é o tipo da classe do EJB.
	 */
	@EJB(beanName = "AppBean", beanInterface = AppBean.class)
	private AppBean myAppBean;

	@EJB(beanName = "AppBean2")
	// (beanName = "teste") Erro pois nao existe nenhum EJB anotado com
	// Stateless ou Stateful com atributo name="teste"
	private AppBean2 appBean2;

	/**
	 * Lookup manual do EJB AppBean. Quando a referencia a um EJB é configurada
	 * no web.xml, é possivel recuperar o EJB referenciado atraves do nome JNDI.
	 */
	public void init() throws ServletException {
		// try {
		// this.appBean = InitialContext.doLookup("java:module/AppBean");
		/*
		 * Se a referencia a este EJB estivesse configurada no web.xml, o lookup
		 * deveria ser feito utilizando o nome JNDI padrao da especificacao Java
		 * EE:
		 */
		// this.appBean = InitialContext.doLookup("java:comp/env/AppBean");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.dataSource);
		// resp.getWriter().println(this.appBean);
		resp.getWriter().println(this.myAppBean);
		resp.getWriter().println(this.appBean2);
	}

}
