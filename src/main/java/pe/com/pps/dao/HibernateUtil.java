package pe.com.pps.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pe.com.pps.model.TipoDeCambio;

public class HibernateUtil {

	protected static Configuration configuration;
	private static SessionFactory sessionFactory = null;

	static {
		configuration = new Configuration();
		configuration.addAnnotatedClass(TipoDeCambio.class);
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}