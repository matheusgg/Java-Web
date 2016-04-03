package br.com.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.utils.Constants;
import br.com.utils.TipoConsulta;

public abstract class AbstractDAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6869639066726719598L;

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		/*
		 * Recupera o tipo generico informado
		 */
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public T fint(Integer id) {
		return (T) this.entityManager.find(this.clazz, id);
	}

	public Long count(Criteria criteria) {
		long count = 0;
		if (criteria != null) {
			criteria = criteria.setProjection(Projections.count("id")).setFirstResult(0);
			count = (Long) criteria.uniqueResult();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(Criteria criteria) {
		List<T> list = new ArrayList<>();
		if (criteria != null) {
			list.addAll(criteria.list());
		}
		return list;
	}

	/**
	 * Prepara o objeto criteria que sera responsavel pela consulta adicionando
	 * os joins necessarios na query e os paremetros da clausula where de acondo
	 * com os filtros informados.
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param filtros
	 * @return
	 */
	protected Criteria prepareCriteria(int startIndex, int pageSize, Map<String, Object> filtros, TipoConsulta tipo) {
		/*
		 * Recupera a sessao do Entity Manager
		 */
		Session session = (Session) this.entityManager.getDelegate();
		Criteria criteria = null;

		/*
		 * Inicializa o objeto criteria
		 */
		criteria = session.createCriteria(this.clazz);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);

		/*
		 * Prepara os campos do objeto criteria adicionando os Joins na query
		 */
		criteria = this.preparaCriteriaFields(criteria, filtros);

		if (!this.isFiltrosVazios(filtros)) {
			/*
			 * Adiciona os parametros da clausula where na consulta
			 */
			criteria = this.addParametrosPesquisa(criteria, filtros, tipo);

		}

		return criteria;
	}

	/**
	 * Verifica se os filtros contendo os parametros esta vazio.
	 * 
	 * @param filtros
	 * @return Um booleano indicando se o filtro esta vazio ou nao.
	 */
	private boolean isFiltrosVazios(Map<String, Object> filtros) {
		boolean empty = true;
		if (filtros != null && filtros.size() > 0) {
			empty = false;
		}
		return empty;
	}

	/**
	 * Prepara o objeto criteria responsavel pela pesquisa e adiciona os joins
	 * na query criando os alias necessarios para definir as clausulas da
	 * consulta.
	 * 
	 * @param criteria
	 * @param filtros
	 * @return
	 */
	private Criteria preparaCriteriaFields(Criteria criteria, Map<String, Object> filtros) {
		for (String key : filtros.keySet()) {
			if (key.contains(Constants.PONTO)) {
				String[] alias = key.split(Constants.BARRAS_PONTO);
				for (int i = 0; i < alias.length - 1; i++) {
					String attr = null;
					if (i > 0) {
						attr = alias[i - 1] + Constants.PONTO + alias[i];
					} else {
						attr = alias[i];
					}
					if (!criteria.toString().contains(Constants.DOIS_PONTOS + alias[i])) {
						criteria = criteria.createAlias(attr, alias[i]);
					}
				}
			}
		}
		return criteria;
	}

	/**
	 * Adiciona os parametros da clausula where na consulta se os mesmos nao
	 * forem nulos ou vazios.
	 * 
	 * @param criteria
	 * @param filtros
	 * @return
	 */
	private Criteria addParametrosPesquisa(Criteria criteria, Map<String, Object> filtros, TipoConsulta tipo) {
		for (String key : filtros.keySet()) {
			Object value = filtros.get(key);
			if (value != null && value.toString().length() > 0 && !value.toString().equals("null")) {
				String[] alias = key.split(Constants.BARRAS_PONTO);
				if (alias.length > 1) {
					criteria = criteria.add(this.addCriterion(tipo, criteria, alias[alias.length - 2] + Constants.PONTO + alias[alias.length - 1], value));
				} else {
					criteria = criteria.add(this.addCriterion(tipo, criteria, alias[alias.length - 1], value));
				}
			}
		}
		return criteria;
	}

	/**
	 * Adiciona a clausula where na query de acordo como tipo informado.
	 * 
	 * @param tipo
	 * @param criteria
	 * @param alias
	 * @param value
	 * @return
	 */
	private Criterion addCriterion(TipoConsulta tipo, Criteria criteria, String alias, Object value) {
		Criterion criterion = null;
		if (tipo == TipoConsulta.LIKE && value instanceof String) {
			criterion = Restrictions.like(alias, value.toString(), MatchMode.ANYWHERE);
		} else if (tipo == TipoConsulta.EQ || value instanceof Integer) {
			criterion = Restrictions.eq(alias, value);
		}
		return criterion;
	}

}
