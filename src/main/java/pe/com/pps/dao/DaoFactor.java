package pe.com.pps.dao;

import pe.com.pps.model.Factor;
import pe.com.pps.model.Parametro;
import pe.trazos.dao.Dao;
import pe.trazos.dao.query.QueryFiltrado;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * clase para data access de la entidad factor
 */
public class DaoFactor<T extends Factor> extends Dao<T> {

	/**
	 * constructor
	 */
	public DaoFactor() {
		super();
	}

	public List<Factor> listarFactores() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Factor> q = cb.createQuery(Factor.class);
		Root<Factor> c = q.from(Factor.class);
		q.orderBy(cb.asc(c.get("nombre")));
		TypedQuery<Factor> query = getSession().createQuery(q);
		List<Factor> params = query.getResultList();
		return new ArrayList<>(params);
	}

}
