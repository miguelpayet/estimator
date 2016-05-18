package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.PuntoCasoDeUso;

public class DaoPuntoCasoDeUso extends Dao<PuntoCasoDeUso> {

	public DaoPuntoCasoDeUso() {
		super(PuntoCasoDeUso.class);
	}

	public PuntoCasoDeUso get(Integer unTipo, Integer unaComplejidad) {
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		crit.add(Restrictions.eq("tipo", unTipo));
		crit.add(Restrictions.eq("complejidad", unaComplejidad));
		return (PuntoCasoDeUso) crit.uniqueResult();
	}

}
