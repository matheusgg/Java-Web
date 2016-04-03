package br.com.lazy.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		Configuration cfg = null;
		ServiceRegistry service = null;
		try {
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			service = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cfg.buildSessionFactory(service);
	}

	/**
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

}
