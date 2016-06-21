package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Parametro;
import pe.com.pps.model.ParametroPK;

import java.util.List;
import java.util.stream.Collectors;

public class DaoParametro extends DaoPK<Parametro, ParametroPK> {

	private static final String PARAMETRO_DESVIACION = "Desviación";
	private static final String PARAMETRO_EDS = "EDS";

	public DaoParametro() {
		super(Parametro.class);
	}

	public Parametro getDesviacion() {
		ParametroPK pk = new ParametroPK(PARAMETRO_DESVIACION, 1);
		return get(pk);
	}

	public List<String> getNombreEds() {
		Criteria crit = crearCriteria();
		crit.add(Restrictions.eq("tipo", PARAMETRO_EDS));
		crit.addOrder(Order.asc("codigo"));
		List<Parametro> params = crit.list();
		return params.stream().map(Parametro::getValor).collect(Collectors.toList());
	}

}
