package br.com.ok.model.dao.base;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.AccessLevel;
import lombok.Getter;

import org.hibernate.Hibernate;
import org.hibernate.internal.SessionImpl;

/**
 * The Class OKGenericDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 * @param <T>
 *            the generic type
 */
@SuppressWarnings("unchecked")
public abstract class OKGenericDAO<T> implements IOKGenericDAO<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7352366528642861477L;

	/** The em. */

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	@Getter(AccessLevel.PROTECTED)
	@PersistenceContext
	private EntityManager entityManager;

	/** The clazz. */
	private Class<T> clazz;

	/**
	 * Instantiates a new OK generic dao.
	 */
	public OKGenericDAO() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @see br.com.ok.model.dao.IOKGenericDAO#save(java.lang.Object)
	 */
	@Override
	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	/**
	 * Removes the.
	 *
	 * @param entity
	 *            the entity
	 * @see br.com.ok.model.dao.IOKGenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(T entity) {
		this.entityManager.remove(entity);
	}

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 * @see br.com.ok.model.dao.IOKGenericDAO#update(java.lang.Object)
	 */
	@Override
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	/**
	 * Detach.
	 *
	 * @param entity
	 *            the entity
	 * @see br.com.ok.model.dao.IOKGenericDAO#detach(java.lang.Object)
	 */
	@Override
	public void detach(T entity) {
		this.entityManager.detach(entity);
	}

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 * @see br.com.ok.model.dao.IOKGenericDAO#findById(java.lang.Long)
	 */
	@Override
	public T findById(Integer id) {
		return this.entityManager.find(this.clazz, id);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 * @see br.com.ok.model.dao.IOKGenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		return this.entityManager.createNamedQuery(this.clazz.getSimpleName() + ".findAll").getResultList();
	}

	/**
	 * Inits the proxy.
	 *
	 * @param <P>
	 *            the generic type
	 * @param proxy
	 *            the proxy
	 * @return the p
	 * @see br.com.ok.model.dao.base.IOKGenericDAO#initProxy(java.lang.Object)
	 */
	@Override
	public <P> P initProxy(P proxy) {
		Hibernate.initialize(proxy);
		return proxy;
	}

	/**
	 * Gets the delegate.
	 *
	 * @return the delegate
	 */
	protected SessionImpl getDelegate() {
		return (SessionImpl) this.entityManager.getDelegate();
	}

	/**
	 * Gets the connection from delegate.
	 *
	 * @return the connection from delegate
	 */
	protected Connection getConnectionFromDelegate() {
		return this.getDelegate().connection();
	}

}
