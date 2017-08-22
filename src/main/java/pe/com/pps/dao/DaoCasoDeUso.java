package pe.com.pps.dao;

import pe.com.pps.model.CasoDeUso;
import pe.trazos.dao.Dao;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad caso de uso
 */
@DataAccessObject(CasoDeUso.class)
public class DaoCasoDeUso extends Dao<CasoDeUso> {

	/**
	 * constructor
	 */
	public DaoCasoDeUso() {
		super();
	}

}
