package com.livro.capitulo3.conexao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {	
	/*
	 * Objeto que representa a conexão com o banco
	 */
	private static final SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * Método que ler o arquivo hibernate.cfg.xml
	 * e configura a conexão
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration(); //Objeto que configura o xml
			cfg.configure("hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder() // Registro do serviço inplementado a partir da versão 4
					.applySettings(cfg.getProperties()).buildServiceRegistry();		

			return cfg.buildSessionFactory(serviceRegistry); //retorno da conexão 
		} catch (Exception ex) {
			System.err.println("Erro na criação do SessionFactory "
					+ ex.getMessage());
			return null;
		}

	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
