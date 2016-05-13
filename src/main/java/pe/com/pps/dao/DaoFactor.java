package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import pe.com.pps.model.Factor;

import java.util.List;

public class DaoFactor extends Dao<Factor> {

	public DaoFactor() {
		super(Factor.class);
	}

	public List<Factor> getAll() {
		Criteria crit = getSesion().createCriteria(Factor.class);
		crit.addOrder(Order.asc("tipoFactor"));
		crit.addOrder(Order.asc("nombre"));
		return crit.list();
	}

}
