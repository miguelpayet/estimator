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
		añadirClasesAnotadas();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	private static void añadirClasesAnotadas() {
		configuration.addAnnotatedClass(Actor.class);
		configuration.addAnnotatedClass(CasoDeUso.class);
		configuration.addAnnotatedClass(Complejidad.class);
		configuration.addAnnotatedClass(Estimacion.class);
		configuration.addAnnotatedClass(Factor.class);
		configuration.addAnnotatedClass(FactorAmbiental.class);
		configuration.addAnnotatedClass(FactorEstimacion.class);
		configuration.addAnnotatedClass(FactorTecnico.class);
		configuration.addAnnotatedClass(Plataforma.class);
		configuration.addAnnotatedClass(Proveedor.class);
		configuration.addAnnotatedClass(Punto.class);
		configuration.addAnnotatedClass(Tarea.class);
		configuration.addAnnotatedClass(TareaCronograma.class);
		configuration.addAnnotatedClass(TareaCronogramaPK.class);
		configuration.addAnnotatedClass(TareaDuracion.class);
		configuration.addAnnotatedClass(TareaEsfuerzo.class);
		configuration.addAnnotatedClass(TareaFija.class);
		configuration.addAnnotatedClass(TareaGestion.class);
		configuration.addAnnotatedClass(TipoCosto.class);
		configuration.addAnnotatedClass(TipoFactor.class);
		configuration.addAnnotatedClass(TipoPunto.class);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}