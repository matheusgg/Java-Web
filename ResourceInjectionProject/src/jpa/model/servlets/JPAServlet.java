package jpa.model.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AppBean;

/**
 * É possivel utilizar a anotacao PersistenceUnits para agrupar varias anotacoes
 * PersistenceUnit. Desta forma é possivel especificar unidades de persistencia
 * diferentes.
 * 
 * @author Matheus
 * 
 */
// @PersistenceUnits({ @PersistenceUnit(name =
// "java:comp/env/persistence/ResourceInjectionProject", unitName =
// "ResourceInjectionProject") })

/**
 * É possivel anotar um componente web com PersistenceUnit, desta forma o
 * container cria uma referencia para um EntityManagerFactory com as
 * configuracoes da unidade de persistencia informada. Além disso, o container
 * associa essa referencia com o nome JNDI
 * "java:comp/env/valor do atributo name da PersistenceUnit".
 * 
 * @author Matheus
 * 
 */
@PersistenceUnit(name = "ResourceInjectionProject", unitName = "ResourceInjectionProject")
/**
 * Assim como PersistenceUnit é possivel utilizar a anotacao EJBs para agrupar 
 * varias anotacoes EJB, desta forma o container faz uma linkagem (binding) das referencias 
 * representadas pelas anotacoes EJB com o nome JNDI "java:comp/env/valor do atributo name da anotacao".
 * Deste modo, é possivel utilizar o mecanismo de lookup para recuperar as instancias de EJB associadas
 * a essas referencias.
 * 
 * @author Matheus
 *
 */
@EJBs({ @EJB(beanName = "AppBean", name = "teste", beanInterface = AppBean.class) })
@WebServlet("/JPAServlet")
public class JPAServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4957244417364565111L;

	private EntityManagerFactory emf;

	@Override
	public void init() throws ServletException {
		try {
			PersistenceUnit persistenceUnit = this.getClass().getAnnotation(PersistenceUnit.class);

			/*
			 * Aqui é feito o lookup do EntityManagerFactory de acordo com o
			 * nome definido na anotacao PersistenceUnit concatenado a
			 * java:comp/env/
			 */
			this.emf = InitialContext.doLookup("java:comp/env/" + persistenceUnit.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.emf);
	}

}
