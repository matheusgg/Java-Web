package jpa.model.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JPAServlet2")
public class JPAServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4957244417364565111L;

	/*
	 * A anotacao PersistenceUnit é utilizada para recuperar uma instancia e
	 * injetar um EntityManagerFactory configurado de acordo com as informacoes
	 * da unidade de persistencia informada. No caso de haver apenas uma
	 * persistenceUnit, o atributo unitName é opcional.
	 */
	@PersistenceUnit(unitName = "ResourceInjectionProject")
	private EntityManagerFactory emf;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.emf);
	}

}
