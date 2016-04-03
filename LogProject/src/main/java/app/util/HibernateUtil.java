package app.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();	
	
	private static SessionFactory buildSessionFactory(){
		logger.info("Iniciando configura��o do banco...");
		Configuration cfg = null;
		ServiceRegistry service = null;		
		try{
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			service = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		}catch(HibernateException ex){
			logger.error(ex.getMessage());
		}
		logger.info("Configura��o conclu�da. Conectado!");
		return cfg.buildSessionFactory(service);
	}
	
	public static Session openSession(){
		Session sessao = null;
		try{
			logger.info("Abrindo sess�o...");
			sessao = sessionFactory.openSession();		
			sessao.beginTransaction();
		}catch(HibernateException ex){
			logger.error(ex.getMessage());
		}
		return sessao;
	}
	
	public static void commit(Session sessao){
		try{
			if(sessao != null && sessao.isOpen()){
				logger.info("Comitando transa��o...");
				sessao.getTransaction().commit();
				sessao.close();
				logger.info("Sess�o encerrada!");
			}
		}catch(HibernateException ex){
			logger.error(ex.getMessage());
		}
	}

}
