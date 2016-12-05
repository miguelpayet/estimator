package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Parametro;
import pe.com.pps.model.ParametroPK;

import java.util.List;
import java.util.stream.Collectors;

/**
 * clase para clave primaria de la entidad parámetro de la estimación
 */
public class DaoParametro extends DaoPK<Parametro, ParametroPK> {

	private static final String PARAMETRO_DESVIACION = "Desviación";
	private static final String PARAMETRO_EDS = "EDS";
	private static final String PARAMETRO_MONEDA = "Moneda";
	private static final String PARAMETRO_PRODUCTIVIDAD_ACTOR = "ProductividadActor";

	/**
	 * constructor
	 */
	public DaoParametro() {
		super(Parametro.class);
	}

	/**
	 * @return el parámetro de desviación
	 */
	public Parametro getDesviacion() {
		return getParametroUnico(PARAMETRO_DESVIACION);
	}

	/**
	 * @return el parámetro de productividad del actor
	 */
	public Parametro getFactorProductividadActor() {
		return getParametroUnico(PARAMETRO_PRODUCTIVIDAD_ACTOR);
	}

	/**
	 * obtiene la lista de valores de un parámetro
	 *
	 * @param unParametro -> parámetro cuya lista se busca
	 * @return lista de valores del parámetro
	 */
	public List<String> getLista(String unParametro) {
		Criteria crit = crearCriteria();
		crit.add(Restrictions.eq("tipo", unParametro));
		crit.addOrder(Order.asc("codigo"));
		List<Parametro> params = crit.list();
		return params.stream().map(Parametro::getValor).collect(Collectors.toList());
	}

	/**
	 * @return el parámetro de moneda
	 */
	public List<String> getMoneda() {
		return getLista(PARAMETRO_MONEDA);
	}

	/**
	 * @return lista de eds configurada
	 */
	public List<String> getNombreEds() {
		return getLista(PARAMETRO_EDS);
	}

	/**
	 * obtener un parámetro que es único - osea que no es una lista
	 *
	 * @param unParametro -> nombre del parámetro
	 * @return valor único del parámetro
	 */
	public Parametro getParametroUnico(String unParametro) {
		ParametroPK pk = new ParametroPK(unParametro, 1);
		return get(pk);
	}

}
