package pe.com.pps.model;

import pe.com.pps.dao.DaoParametro;

public class Desviacion {

	public static Double porcentaje() {
		DaoParametro dp = new DaoParametro();
		return dp.getDesviacion().getValorDouble() * 100d;
	}

}
