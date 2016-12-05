package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Punto;

/**
 * clase para data access de la entidad punto
 */
public class DaoPunto extends Dao<Punto> {

	/**
	 * constructor
	 */
	public DaoPunto() {
		super(Punto.class);
	}

	/**
	 * obtener el punto correspondiente a un factor según el tipo y la complejidad
	 *
	 * @param unTipo         -> tipo de factor
	 * @param unaComplejidad -> complejidad del factor
	 * @return obtener el objeto de clase punto correspondiente
	 */
	public Punto get(Integer unTipo, Integer unaComplejidad) {
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		crit.add(Restrictions.eq("tipo", unTipo));
		crit.add(Restrictions.eq("complejidad", unaComplejidad));
		return (Punto) crit.uniqueResult();
	}

}
