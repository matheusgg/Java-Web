package br.com.maestroautomacoes.portal.model.dao.util;

import java.io.Serializable;

import org.hibernate.Session;

public abstract class AbstractDao<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5039871124760788113L;
	protected Session sessao;

	public void salva(T object) {
		this.sessao.save(object);
	}

	public void atualiza(T object) {
		this.sessao.update(object);
	}

	public void remove(T object) {
		this.sessao.delete(object);
	}

	@SuppressWarnings("unchecked")
	public T find(Class<T> type, int id) {
		return (T) this.sessao.get(type, id);
	}

}
