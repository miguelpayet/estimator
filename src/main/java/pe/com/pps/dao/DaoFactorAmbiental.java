package pe.com.pps.dao;

import pe.com.pps.model.FactorAmbiental;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad factor técnico
 */
@DataAccessObject(FactorAmbiental.class)
class DaoFactorAmbiental extends DaoFactor<FactorAmbiental> {

	/**
	 * constructor
	 */
	public DaoFactorAmbiental() {
		super();
	}

}
