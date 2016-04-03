package com.livro.capitulo3.conexao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {	
	/*
	 * Objeto que representa a conex�o com o banco
	 */
	private static final SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * M�todo que ler o arquivo hibernate.cfg.xml
	 * e configura a conex�o
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration(); //Objeto que configura o xml
			cfg.configure("hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder() // Registro do servi�o inplementado a partir da vers�o 4
					.applySettings(cfg.getProperties()).buildServiceRegistry();		

			return cfg.buildSessionFactory(serviceRegistry); //retorno da conex�o 
		} catch (Exception ex) {
			System.err.println("Erro na cria��o do SessionFactory "
					+ ex.getMessage());
			return null;
		}

	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
