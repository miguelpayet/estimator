package pe.com.pps.dao;

import pe.com.pps.model.Estimacion;

public class DaoEstimacion extends Dao<Estimacion> {

	public DaoEstimacion() {
		super(Estimacion.class);
	}

	public void borrar(Estimacion unaEstimacion) {
		getSesion().delete(unaEstimacion);
	}

}
