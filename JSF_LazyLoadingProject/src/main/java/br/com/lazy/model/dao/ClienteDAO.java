package br.com.lazy.model.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.lazy.model.Cliente;

public class ClienteDAO {
	private Session session;

	public void salva(Cliente cliente) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.session.getTransaction().begin();
		this.session.persist(cliente);
		this.session.getTransaction().commit();
		this.session.close();
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> busca(HashMap<String, String> filtros, int first, int max) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		Criteria filtro = this.session.createCriteria(Cliente.class);

		// Adição das condições à query
		for (String propertyKey : filtros.keySet()) {
			String propertyValue = filtros.get(propertyKey);
			if (propertyValue != null && propertyValue.trim().length() > 0) {
				filtro.add(Restrictions.like(propertyKey, propertyValue, MatchMode.START));
			}
		}

		// Definição da quantidade máxima de resultados que serão retornados
		filtro.setFirstResult(first);
		filtro.setMaxResults(max);

		List<Cliente> clientes = filtro.list();

		Criteria countQuery = filtro.setProjection(Projections.rowCount());
		Long countLong = (Long) countQuery.uniqueResult();

		int count = 100;

		if (countLong != null) {
			count = countLong.intValue();
		}

		HashMap<String, Object> retorno = new HashMap<String, Object>();
		retorno.put("count", count);
		retorno.put("lista", clientes);
		return retorno;
	}

}
