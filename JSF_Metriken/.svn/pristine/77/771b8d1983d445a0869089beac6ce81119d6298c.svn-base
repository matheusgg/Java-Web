package br.com.metriken.facade.base;

import java.io.Serializable;

import br.com.metriken.model.dao.base.AbstractGenericDAO;

/**
 * Classe abstrata que deve ser estendida pelas classes responsáveis pela
 * manipulação das regras de negócio. Na classe concreta, o DAO deverá ser
 * encapsulado para manter a segurança e a consistencia do sistema.
 * 
 * @author Matheus
 */
public abstract class AbstractFacade<T extends AbstractGenericDAO<?>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7318266329025345229L;

	private T dao;

	/**
	 * Recupera o tipo parametrizado declarado na classe para criar uma nova
	 * instância do DAO.
	 */
	public AbstractFacade(Class<T> dao) {
		try {
			this.dao = dao.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the dao
	 */
	protected T getDao() {
		return dao;
	}
}
