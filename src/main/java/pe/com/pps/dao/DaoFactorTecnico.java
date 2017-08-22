package pe.com.pps.dao;

import pe.com.pps.model.FactorTecnico;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad factor técnico
 */
@DataAccessObject(FactorTecnico.class)
public class DaoFactorTecnico extends DaoFactor<FactorTecnico> {

	/**
	 * constructor
	 */
	public DaoFactorTecnico() {
		super();
	}

}
