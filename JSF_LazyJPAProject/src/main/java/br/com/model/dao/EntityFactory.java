package br.com.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityFactory {
	private static final EntityManagerFactory emf = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("LazyJPAProject");
	}

	/**
	 * @return the emf
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}

}
