package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Punto;

public class DaoPunto extends Dao<Punto> {

	public DaoPunto() {
		super(Punto.class);
	}

	public Punto get(Integer unTipo, Integer unaComplejidad) {
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		crit.add(Restrictions.eq("tipo", unTipo));
		crit.add(Restrictions.eq("complejidad", unaComplejidad));
		return (Punto) crit.uniqueResult();
	}

}
