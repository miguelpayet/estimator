package pe.com.pps.model;

import pe.com.pps.dao.DaoTarea;

import java.util.List;

public class EstimacionFactory {

	public static Estimacion crear() {
		Estimacion est = new Estimacion();
		DaoTarea dt = new DaoTarea();
		List<Tarea> tareas = dt.listar();
		tareas.forEach(est::addTareaCronograma);
		return est;
	}

}
