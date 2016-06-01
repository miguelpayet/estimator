package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Factor;

import java.util.List;

public class DaoFactor<T extends Factor> extends Dao<T> {

	public DaoFactor(Class unaClase) {
		super(unaClase);
	}

	public List<T> getPorTipo(Integer unTipo) {
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		crit.add(Restrictions.eq("tipoFactor", unTipo));
		crit.addOrder(Order.asc("nombre"));
		return crit.list();
	}

	public List<Factor> listarFactores() {
		Criteria crit = getSesion().createCriteria(Factor.class);
		return crit.list();
	}

}
