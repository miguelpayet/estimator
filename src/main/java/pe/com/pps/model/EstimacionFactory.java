package pe.com.pps.model;

import pe.com.pps.dao.DaoFactorTecnico;
import pe.com.pps.dao.DaoTarea;
import pe.trazos.login.dao.DaoUsuario;
import pe.trazos.login.modelo.Usuario;

import java.util.HashSet;
import java.util.List;

/**
 * forma de crear una estimación e inicializar las estructuras de datos asociadas
 */
public class EstimacionFactory {

	public static Estimacion crear() {
		Estimacion est = new Estimacion();
		// tareas
		DaoTarea dt = new DaoTarea();
		List<Tarea> tareas = dt.listar();
		tareas.forEach(est::addTareaCronograma);
		// factores
		DaoFactorTecnico df = new DaoFactorTecnico();
		for (Factor f : df.listarFactores()) {
			FactorEstimacion fet = new FactorEstimacion();
			fet.setFactor(f);
			fet.setValor(f.getMinimo());
			est.addFactorEstimacion(fet);
		}
		// usuario
		DaoUsuario du = new DaoUsuario();
		Usuario u = du.getUsuarioActual();
		if (u == null) {
			throw new RuntimeException("no hay usuario autentificado");
		}
		est.setUsuario(du.getUsuarioActual());
		return est;
	}

}
