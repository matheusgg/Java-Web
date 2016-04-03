package br.com.ok.model.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface IOKGenericDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 * @param <T>
 *            the generic type
 */
public interface IOKGenericDAO<T> extends Serializable {

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 */
	void save(T entity);

	/**
	 * Removes the.
	 *
	 * @param entity
	 *            the entity
	 */
	void remove(T entity);

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 */
	T update(T entity);

	/**
	 * Detach.
	 *
	 * @param entity
	 *            the entity
	 */
	void detach(T entity);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T findById(Integer id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();

	/**
	 * Inits the proxy.
	 *
	 * @param <P>
	 *            the generic type
	 * @param proxy
	 *            the proxy
	 * @return the p
	 */
	<P> P initProxy(P proxy);

}
