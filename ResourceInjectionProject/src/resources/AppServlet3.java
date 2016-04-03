package resources;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Com Resource é possivel especificar e agrupar varias anotacoes Resource.
 * Serve para especificar os recursos que devem ser injetados em uma classe.
 * Porem desta forma, o trabalho de lookup dos recursos fica por conta do
 * programador e nao do container. Resumindo, é possivel especificar a
 * annotation Resources ou Resource em classes apenas para informar as
 * configuracoes de recursos que devem ser injetados em campos dessa classe,
 * porem a tarefa de injecao fica a cargo do programador.
 * 
 * @author Matheus
 * 
 */
// Jboss
@Resources({ @Resource(name = "java:jboss/datasources/MySQL", description = "dataSource1", type = DataSource.class),
		@Resource(name = "java:jboss/datasources/MySQL", description = "dataSource2", type = DataSource.class) })
// tomcat
// @Resources({ @Resource(name = "java:comp/env/app_db", description =
// "dataSource1", type = DataSource.class),
// @Resource(name = "java:comp/env/app_db2", description = "dataSource2",
// type = DataSource.class) })
@WebServlet("/AppServlet3")
public class AppServlet3 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	private DataSource dataSource1;

	private DataSource dataSource2;

	@Override
	public void init() throws ServletException {
		this.injectResources();
	}

	/**
	 * É possivel especificar a anotacao Resource (ou Resources) em classes,
	 * porem a tarefa de lookup e injecao dos recursos fica por conta do
	 * programador e nao mais do container.
	 */
	@SuppressWarnings("unchecked")
	private void injectResources() {
		Class<AppServlet3> clazz = (Class<AppServlet3>) this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Resources resources = clazz.getAnnotation(Resources.class);

		try {
			for (Resource resource : resources.value()) {
				for (Field field : fields) {
					if (field.getName().equals(resource.description()) && DataSource.class.isAssignableFrom(field.getType())) {
						field.setAccessible(true);

						String prefix = "";
						if (!super.getServletContext().getServerInfo().contains("JBoss")) {
							prefix = "java:comp/env/";
						}

						field.set(this, InitialContext.doLookup(prefix + resource.name()));
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;

		try {
			con = this.dataSource1.getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) as count from cliente;");
			while (resultSet.next()) {
				resp.getWriter().println("<h1>Quantidade de usuarios cadastrados: " + resultSet.getInt("count") + "</h1>");
			}
		} catch (Exception e) {
			super.getServletContext().log(e.getMessage());
		} finally {
			this.closeConnection(con);
		}

		resp.getWriter().println("<h3>DataSource 2: " + this.dataSource2 + "</h3>");
	}

	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			super.getServletContext().log(e.getMessage());
		}
	}
}
