package pe.com.pps.dao;

import pe.com.pps.model.Estimacion;

public class DaoEstimacion extends Dao<Estimacion> {

	public DaoEstimacion() {
		super(Estimacion.class);
	}

	public void borrar(Estimacion unaEstimacion) {
		getSesion().delete(unaEstimacion);
	}

	public void grabar(Estimacion unaEstimacion) {
		unaEstimacion.totalizar(true);
		if (unaEstimacion.getVersion() == null) {
			getSesion().persist(unaEstimacion);
		} else {
			getSesion().update(unaEstimacion);
		}
	}

}
