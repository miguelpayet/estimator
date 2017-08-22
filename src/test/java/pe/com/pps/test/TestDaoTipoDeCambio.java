package pe.com.pps.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import pe.com.pps.dao.DaoTipoDeCambio;
import pe.trazos.dao.factory.DaoFactory;

public class TestDaoTipoDeCambio {

	public static Logger log = LogManager.getLogger(TestDaoTipoDeCambio.class);

	@Test
	public void leerTipoDeCambio() {
		//DaoTipoDeCambio dtc = DaoFactory.getInstance().crearDao(TipoDeCambio.class, DaoTipoDeCambio.class);
		//Double tipoDeCambio = dtc.leerTipoDeCambio();
		//log.info("tipo de cambio: {}", tipoDeCambio);
	}

}
