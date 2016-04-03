package resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406350029561086107L;

	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		try {

			/*
			 * O nome JNDI do recurso mapeado no web.xml. Por padrao sempre
			 * comeca com java:comp/env.
			 */
			this.dataSource = InitialContext.doLookup("java:jboss/datasources/MySQL"); // Jboss
			// this.dataSource =
			// InitialContext.doLookup("java:comp/env/jdbc/app_db"); // Tomcat

		} catch (NamingException e) {
			super.getServletContext().log(e.getMessage());
		}

	}

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

}
