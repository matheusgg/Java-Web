package resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * A anotacao Resource permite a injecao de DataSources, EJB's entre outros.
 * 
 * @author Matheus
 * 
 */
@WebServlet("/AppServlet2")
public class AppServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	/**
	 * É possivel utilizar annotations para injetar recursos. Com @Resource,
	 * basta especificar o nome do resource mapeado no web.xml. Este nome sera
	 * concatenado a java:comp/env e formara o nome do recurso. Nao é necessario
	 * especificar mais nenhum configuracao, uma vez que o modo de autenticacao
	 * é Container por padrao e o tipo do recurso é descoberto pelo tipo do
	 * campo anotado, desta forma o container podera fazer o cast correto.
	 * Quando a anotacao é utilizada, nao ha necessidade de configurar e mapear
	 * o recurso no web.xml, neste caso, jdbc/app_db2 nao esta mepeado no
	 * web.xml. Caso o atributo name nao for informado, o nome padrao utilizado
	 * pelo container sera o nome completo da classe seguido pelo nome do campo.
	 * Caso esta anotacao seja aplicada para uma classe o atributo name é
	 * obrigatorio.
	 */
	// @Resource(name = "java:jboss/datasources/MySQL") // Jboss
	// @Resource(name = "java:comp/env/app_db") // tomcat
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;

		try {
			con = this.dataSource.getConnection();
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

	/**
	 * É possivel especificar a anotacao Resource em metodos, porem os mesmos
	 * devem seguir alguns paroes, ou seja, devem seguir a nomeclatura JavaBeans
	 * (deve ser um metodo setXxx), alem disso nao devem ter retorno e devem
	 * possuir apenas um parametro. Os recursoso sao injetados antes da primeira
	 * vez que a classe é instanciada. Caso o atributo name nao seja
	 * especificado, o padrao sera o nome completo da classe seguido pelo nome
	 * do atributo representado por este metodo set seguindo os padroes
	 * JavaBeans.
	 * 
	 * @param ds
	 */
	// Jboss
	@Resource(name = "java:jboss/datasources/MySQL")
	// tomcat
	// @Resource(name = "java:comp/env/app_db")
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}

}
