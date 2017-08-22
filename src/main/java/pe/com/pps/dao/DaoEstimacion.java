package pe.com.pps.dao;

import pe.com.pps.model.Estimacion;
import pe.trazos.dao.Dao;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad estimación
 */
@DataAccessObject(Estimacion.class)
public class DaoEstimacion extends Dao<Estimacion> {

	/**
	 * constructor
	 */
	public DaoEstimacion() {
		super();
	}

	/**
	 * grabar estimación en la base de datos
	 *
	 * @param unaEstimacion -> estimación a grabar
	 */
	public void grabar(Estimacion unaEstimacion) {
		unaEstimacion.totalizar(true);
		super.grabar(unaEstimacion);
	}

}
