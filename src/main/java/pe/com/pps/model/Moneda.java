package pe.com.pps.model;

import pe.com.pps.dao.DaoParametro;

import java.util.List;

public class Moneda {

	public static List<String> getLista() {
		DaoParametro dp = new DaoParametro();
		return dp.getMoneda();
	}

}
