package br.com.jpa2hibernate.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ConnectionFactory {
	private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("livraria");
	}

	/**
	 * @return the entityManagerFactory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * @param entityManagerFactory
	 *            the entityManagerFactory to set
	 */
	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		ConnectionFactory.entityManagerFactory = entityManagerFactory;
	}
	
	public static void close(){
		ConnectionFactory.entityManagerFactory.close();
	}
}
