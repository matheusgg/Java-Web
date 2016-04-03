package jpa.model.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JPAServlet3")
public class JPAServlet3 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4957244417364565111L;

	/*
	 * Com PersistenceContext é possivel recuperar e injetar uma instancia do
	 * EntityManager em um web component apenas se o tipo de transacao
	 * (transaction-type no persistence.xml) for JTA. O nome da unidade é
	 * opcional caso haja apenas uma persistence unit.
	 */
	@PersistenceContext(unitName = "ResourceInjectionProject")
	private EntityManager em;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.em);
	}

}
