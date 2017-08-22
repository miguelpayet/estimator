package pe.com.pps.model;

import pe.com.pps.dao.DaoParametro;
import pe.trazos.dao.factory.DaoFactory;

import java.util.List;

public class Moneda {

	public static List<String> getLista() {
		DaoParametro dp = DaoFactory.getInstance().crearDao(Parametro.class, DaoParametro.class);
		return dp.getMoneda();
	}

	private Moneda() {
		// constructor privado para impedir instanciación
	}

}
