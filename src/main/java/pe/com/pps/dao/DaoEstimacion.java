package pe.com.pps.dao;

import pe.com.pps.model.Estimacion;

/**
 * clase para data access de la entidad estimación
 */
public class DaoEstimacion extends Dao<Estimacion> {

	/**
	 * constructor
	 */
	public DaoEstimacion() {
		super(Estimacion.class);
	}

	/**
	 * grabar estimación en la base de datos
	 *
	 * @param unaEstimacion -> estimación a grabar
	 */
	public void grabar(Estimacion unaEstimacion) {
		unaEstimacion.totalizar(true);
		if (unaEstimacion.getVersion() == null) {
			getSesion().persist(unaEstimacion);
		} else {
			getSesion().update(unaEstimacion);
		}
	}

}
