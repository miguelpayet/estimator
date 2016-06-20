package pe.com.pps.dao;

import pe.com.pps.model.Parametro;
import pe.com.pps.model.ParametroPK;

public class DaoParametro extends DaoPK<Parametro, ParametroPK> {

	private static final String PARAMETRO_DESVIACION = "Desviación";

	public DaoParametro() {
		super(Parametro.class);
	}

	public Parametro getDesviacion() {
		ParametroPK pk = new ParametroPK(PARAMETRO_DESVIACION, 1);
		return get(pk);
	}

}
