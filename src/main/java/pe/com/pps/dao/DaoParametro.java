package pe.com.pps.dao;

import pe.com.pps.model.Parametro;
import pe.com.pps.model.ParametroPK;
import pe.trazos.dao.factory.DataAccessObject;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 * clase para clave primaria de la entidad parámetro de la estimación
 */
@DataAccessObject(Parametro.class)
public class DaoParametro extends DaoPK<Parametro, ParametroPK> {

	private static final String PARAMETRO_DESVIACION = "Desviación";
	private static final String PARAMETRO_EDS = "EDS";
	private static final String PARAMETRO_MONEDA = "Moneda";
	private static final String PARAMETRO_PRODUCTIVIDAD_ACTOR = "ProductividadActor";

	/**
	 * constructor
	 */
	public DaoParametro() {
		super();
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
	@SuppressWarnings("unchecked")
	private List<String> getLista(String unParametro) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Parametro> q = cb.createQuery(Parametro.class);
		Root<Parametro> c = q.from(Parametro.class);
		cb.equal(c.get("tipo"), unParametro);
		q.orderBy(cb.asc(c.get("codigo")));
		TypedQuery<Parametro> query = getSession().createQuery(q);
		List<Parametro> params = query.getResultList();
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
	private Parametro getParametroUnico(String unParametro) {
		ParametroPK pk = new ParametroPK(unParametro, 1);
		return get(pk);
	}

}
