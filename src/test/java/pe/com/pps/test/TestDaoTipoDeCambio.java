package pe.com.pps.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import pe.com.pps.dao.DaoTipoDeCambio;

public class TestDaoTipoDeCambio {

	public static Logger log = LogManager.getLogger(TestDaoTipoDeCambio.class);

	@Test
	public void leerTipoDeCambio() {
		DaoTipoDeCambio dtc = new DaoTipoDeCambio();
		//Double tipoDeCambio = dtc.leerTipoDeCambio();
		//log.info("tipo de cambio: {}", tipoDeCambio);
	}

}
