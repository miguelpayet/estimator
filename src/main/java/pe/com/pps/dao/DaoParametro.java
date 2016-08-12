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
	private static final String PARAMETRO_MONEDA = "Moneda";
	private static final String PARAMETRO_PRODUCTIVIDAD_ACTOR = "ProductividadActor";

	public DaoParametro() {
		super(Parametro.class);
	}

	public Parametro getDesviacion() {
		return getParametroUnico(PARAMETRO_DESVIACION);
	}

	public Parametro getFactorProductividadActor() {
		return getParametroUnico(PARAMETRO_PRODUCTIVIDAD_ACTOR);
	}

	public List<String> getLista(String unParametro) {
		Criteria crit = crearCriteria();
		crit.add(Restrictions.eq("tipo", unParametro));
		crit.addOrder(Order.asc("codigo"));
		List<Parametro> params = crit.list();
		return params.stream().map(Parametro::getValor).collect(Collectors.toList());
	}

	public List<String> getMoneda() {
		return getLista(PARAMETRO_MONEDA);
	}

	public List<String> getNombreEds() {
		return getLista(PARAMETRO_EDS);
	}

	public Parametro getParametroUnico(String unParametro) {
		ParametroPK pk = new ParametroPK(unParametro, 1);
		return get(pk);
	}

}
