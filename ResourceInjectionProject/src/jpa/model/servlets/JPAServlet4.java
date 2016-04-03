package jpa.model.servlets;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Apesar de ser bastante simples, a tecnica de injecao de um EntityManager em
 * uma Servlet ou Filter nao é recomendada, pois Servlets nao sao thread-safe,
 * assim como EntityManagers, desta forma, vários clientes utilizariam a mesma
 * conexao e a mesma servlet ao mesmo tempo, podendo assim gerar algum possivel
 * erro. É recomendado recuperar um EntityManager a cada requisicao recebida
 * pela Servlet (geralmente dentro dos metodos service ou doXxx).
 * 
 * @author Matheus
 * 
 */
@PersistenceContext(name = "manage1")
/*
 * Desta forma, o container nao associara um nome JNDI as referencias
 * representadas pelos PersistenceContext encontrados dentro da anotacao
 * PersistenceContexts. Este comportamento so ocorre quando a classe é anotada
 * com @PersistenceContext.
 */
// @PersistenceContexts({ @PersistenceContext(name = "manage1") })
@WebServlet("/JPAServlet4")
public class JPAServlet4 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4957244417364565111L;

	private EntityManager em;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Inicializando o entity manager. Só é possivel utilizar a anotacao
		 * PersistenceContext quando o tipo da transacao estiver definido para
		 * JTA. Desta forma nao é preciso fechar a conexao, pois a mesma é
		 * gerenciada pelo servidor.
		 */
		this.initEntityManager();

		resp.getWriter().println(this.em);
	}

	private void initEntityManager() {
		try {
			PersistenceContext persistenceContext = this.getClass().getAnnotation(PersistenceContext.class);

			/*
			 * Quando a annotation PersistenceContext é declara em uma classe, o
			 * container associa uma referencia de um EntityManager com um nome
			 * JNDI especificado na anotacao atraves do atributo "name"
			 * concatenado com "java:comp/env/". O mesmo nao é feito quando
			 * varias PersistenceContext sao agrupadas dentro da anotacao
			 * PersistenceContexts
			 */
			this.em = InitialContext.doLookup("java:comp/env/" + persistenceContext.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
