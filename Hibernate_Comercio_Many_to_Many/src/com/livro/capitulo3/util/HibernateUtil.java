package com.livro.capitulo3.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(
				cfg.getProperties()).buildServiceRegistry();

		sessionFactory = cfg.buildSessionFactory(service);
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
