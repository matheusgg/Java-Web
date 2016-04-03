package financeiro.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		Configuration cfg = null;
		ServiceRegistry service = null;

		try {
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			service = new ServiceRegistryBuilder().applySettings(
					cfg.getProperties()).buildServiceRegistry();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return cfg.buildSessionFactory(service);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
