package pe.com.pps.dao;

import pe.trazos.dao.Dao;
import pe.trazos.dao.entidad.EntidadPK;

/*
clase para las pk compuestas
T es la clase del modelo
V es la clase de llave
*/
public class DaoPK<T extends EntidadPK<V>, V> extends Dao<T> {

	/**
	 * constructor
	 */
	public DaoPK() {
		super();
	}

	/**
	 * obtener un objeto según la pk de un registro
	 *
	 * @param unaPk -> una llave
	 * @return el objeto correspondiente
	 */
	@SuppressWarnings("unchecked")
	public T get(V unaPk) {
		return getSession().find(getClaseEntidad(), unaPk);
	}

}
