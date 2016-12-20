package pe.com.pps.dao;

import org.hibernate.Criteria;
import pe.com.pps.model.Factor;

import java.util.List;

/**
 * clase para data access de la entidad factor
 */
public abstract class DaoFactor<T extends Factor> extends Dao<T> {

	/**
	 * constructor
	 *
	 * @param unaClase -> clase de factor
	 */
	DaoFactor(Class unaClase) {
		super(unaClase);
	}

	/**
	 * listar los factores de la entidad
	 *
	 * @return lista de factores
	 */
	@SuppressWarnings("unchecked")
	public List<Factor> listarFactores() {
		Criteria crit = getSesion().createCriteria(Factor.class);
		return crit.list();
	}

}
