package br.com.maestroautomacoes.portal.model.dao.util;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.maestroautomacoes.portal.logger.LogManager;

@Dao
@Interceptor
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = -5036447992693532253L;
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		Configuration cfg = null;
		ServiceRegistry service = null;
		try {
			cfg = new Configuration().configure("hibernate.cfg.xml");
			service = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		} catch (Exception e) {
			String mensagem = LogManager.makeStackTraceLog(e);
			LogManager.getLogger().error(mensagem);
		}
		return cfg.buildSessionFactory(service);
	}

	@AroundInvoke
	private Object intercept(InvocationContext ctx) throws Exception {
		Object obj = null;
		AbstractDao<?> dao = (AbstractDao<?>) ctx.getTarget();
		dao.sessao = HibernateUtil.getSessionfactory().openSession();
		try {
			dao.sessao.beginTransaction();
			obj = ctx.proceed();
			dao.sessao.getTransaction().commit();
		} catch (Exception e) {
			dao.sessao.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			dao.sessao.close();
		}
		return obj;
	}

	/**
	 * @return the sessionfactory
	 */
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
