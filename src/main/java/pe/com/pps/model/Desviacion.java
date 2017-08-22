package pe.com.pps.model;

import pe.com.pps.dao.DaoParametro;
import pe.trazos.dao.factory.DaoFactory;

public class Desviacion {

	public static Double porcentaje() {
		DaoParametro dp = DaoFactory.getInstance().crearDao(Parametro.class, DaoParametro.class);
		return dp.getDesviacion().getValorDouble() * 100d;
	}

}
