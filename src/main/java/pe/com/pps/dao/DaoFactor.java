package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Factor;

import java.util.List;

public abstract class DaoFactor<T extends Factor> extends Dao<T> {

	public DaoFactor(Class unaClase) {
		super(unaClase);
	}

	public List<T> getPorTipo(Integer unTipo) {
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		crit.add(Restrictions.eq("tipoFactor", unTipo));
		crit.addOrder(Order.asc("nombre"));
		return crit.list();
	}

}
