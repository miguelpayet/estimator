package pe.com.pps.dao;

import pe.com.pps.model.Factor;
import pe.trazos.dao.Dao;

/**
 * clase para data access de la entidad factor
 */
abstract class DaoFactor<T extends Factor> extends Dao<T> {

	/**
	 * constructor
	 *
	 * @param unaClase -> clase de factor
	 */
	DaoFactor() {
		super();
	}

}
