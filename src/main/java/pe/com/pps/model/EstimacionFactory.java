package pe.com.pps.model;

import pe.com.pps.dao.DaoFactorTecnico;
import pe.com.pps.dao.DaoTarea;
import pe.trazos.dao.factory.DaoFactory;
import pe.trazos.login.dao.DaoUsuario;
import pe.trazos.login.entidad.Usuario;

import java.util.List;

/**
 * forma de crear una estimación e inicializar las estructuras de datos asociadas
 */

public class EstimacionFactory {

	public static Estimacion crear() {
		Estimacion est = new Estimacion();
		// tareas
		DaoTarea dt = DaoFactory.getInstance().crearDao(Tarea.class, DaoTarea.class);
		List<Tarea> tareas = dt.listar();
		tareas.forEach(est::addTareaCronograma);
		// factores
		DaoFactorTecnico df = DaoFactory.getInstance().crearDao(FactorTecnico.class, DaoFactorTecnico.class);
		for (Factor f : df.listar()) {
			FactorEstimacion fet = new FactorEstimacion();
			fet.setFactor(f);
			fet.setValor(f.getMinimo());
			est.addFactorEstimacion(fet);
		}
		// usuario
		DaoUsuario du = DaoFactory.getInstance().crearDao(Usuario.class, DaoUsuario.class);
		Usuario u = du.getUsuarioActual();
		if (u == null) {
			throw new RuntimeException("no hay usuario autentificado");
		}
		est.setUsuario(du.getUsuarioActual());
		return est;
	}

	private EstimacionFactory() {
		// constructor privado para evitar instanciación
	}

}
