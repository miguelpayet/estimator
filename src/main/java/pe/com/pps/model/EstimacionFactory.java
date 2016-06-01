package pe.com.pps.model;

import pe.com.pps.dao.DaoFactor;
import pe.com.pps.dao.DaoFactorTecnico;
import pe.com.pps.dao.DaoTarea;

import java.util.List;

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
		return est;
	}

}
