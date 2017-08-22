package pe.com.pps.dao;

import pe.com.pps.model.Plataforma;
import pe.trazos.dao.Dao;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad plataforma
 */
@DataAccessObject(Plataforma.class)
public class DaoPlataforma extends Dao<Plataforma> {

	/**
	 * constructor
	 */
	public DaoPlataforma() {
		super();
	}

}
