package pe.com.pps.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pe.com.pps.model.*;
import pe.trazos.dominio.Rol;
import pe.trazos.dominio.TokenNuevoPassword;
import pe.trazos.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class xHibernateUtil {

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
		List<Class> clases = new ArrayList();
		// clases del modelo
		clases.add(Actor.class);
		clases.add(CasoDeUso.class);
		clases.add(Complejidad.class);
		clases.add(Estimacion.class);
		clases.add(Factor.class);
		clases.add(FactorAmbiental.class);
		clases.add(FactorEstimacion.class);
		clases.add(FactorTecnico.class);
		clases.add(Parametro.class);
		clases.add(Plataforma.class);
		clases.add(Proveedor.class);
		clases.add(Punto.class);
		clases.add(Tarea.class);
		clases.add(TareaCronograma.class);
		clases.add(TareaCronogramaPK.class);
		clases.add(TareaDuracion.class);
		clases.add(TareaEsfuerzo.class);
		clases.add(TareaFija.class);
		clases.add(TareaGestion.class);
		clases.add(TipoCosto.class);
		clases.add(TipoFactor.class);
		clases.add(TipoPunto.class);
		// clases del login - todo: independizar
		clases.add(Rol.class);
		clases.add(TokenNuevoPassword.class);
		clases.add(Usuario.class);
		for (Class unaClase : clases) {
			configuration.addAnnotatedClass(unaClase);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}