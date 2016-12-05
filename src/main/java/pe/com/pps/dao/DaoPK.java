package pe.com.pps.dao;

import java.io.Serializable;

/*
clase para las pk compuestas
T es la clase del modelo
V es la clase de llave
*/
public class DaoPK<T, V extends Serializable> extends Dao<T> {

	/**
	 * constructor
	 *
	 * @param unaClase -> clase de la entidad
	 */
	public DaoPK(Class unaClase) {
		super(unaClase);
	}

	/**
	 * obtener un objeto según la pk de un registro
	 *
	 * @param unaPk -> una llave
	 * @return el objeto correspondiente
	 */
	public T get(V unaPk) {
		return (T) getSesion().get(claseModelo, unaPk);
	}

}
