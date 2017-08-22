package pe.com.pps.dao;

import pe.com.pps.model.Punto;
import pe.com.pps.model.PuntoPK;
import pe.trazos.dao.factory.DataAccessObject;

/**
 * clase para data access de la entidad punto
 */
@DataAccessObject(Punto.class)
public class DaoPunto extends DaoPK<Punto, PuntoPK> {

	/**
	 * constructor
	 */
	public DaoPunto() {
		super();
	}

	/**
	 * obtener el punto correspondiente a un factor según el tipo y la complejidad
	 *
	 * @param unTipo         -> tipo de factor
	 * @param unaComplejidad -> complejidad del factor
	 * @return obtener el objeto de clase punto correspondiente
	 */
	public Punto get(Integer unTipo, Integer unaComplejidad) {
		PuntoPK pk = new PuntoPK(unTipo, unaComplejidad);
		return get(pk);
	}

}
