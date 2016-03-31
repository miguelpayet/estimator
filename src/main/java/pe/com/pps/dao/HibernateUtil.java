package pe.com.pps.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pe.com.pps.model.*;

public class HibernateUtil {

	protected static Configuration configuration;
	private static SessionFactory sessionFactory = null;

	static {
		configuration = new Configuration();
		configuration.addAnnotatedClass(Categoria.class);
		configuration.addAnnotatedClass(Complejidad.class);
		configuration.addAnnotatedClass(Estimacion.class);
		configuration.addAnnotatedClass(EstimacionFactor.class);
		configuration.addAnnotatedClass(Factor.class);
		configuration.addAnnotatedClass(Funcionalidad.class);
		configuration.addAnnotatedClass(Interfaz.class);
		configuration.addAnnotatedClass(Plataforma.class);
		configuration.addAnnotatedClass(PlataformaValor.class);
		configuration.addAnnotatedClass(Proveedor.class);
		configuration.addAnnotatedClass(Tarea.class);
		configuration.addAnnotatedClass(TipoDeCambio.class);
		configuration.addAnnotatedClass(TipoDeFactor.class);
		configuration.addAnnotatedClass(TipoDeInterfaz.class);
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}