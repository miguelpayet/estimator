package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Assert;
import org.junit.Test;
import pe.com.pps.dao.DaoCasoDeUso;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;

import java.io.FileInputStream;

public class TestCasoDeUso extends TestBase<Factor> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XlsDataSet(new FileInputStream("datasetCasoDeUso.xlsx"));
	}

	@Test
	public void grabarCasoDeUso() {
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoCasoDeUso dc = new DaoCasoDeUso();
		CasoDeUsoPK pk = new CasoDeUsoPK(est, 1);
		CasoDeUso c = dc.get(pk);
		if (c == null) {
			c = new CasoDeUso();
			c.setEstimacion(est);
			c.setComplejidad(1);
			c.setNumCaso(1);
			c.setDescripcion("Hola");
			DaoPlataforma dp = new DaoPlataforma();
			Plataforma p = dp.get(1);
			c.setPlataforma(p);
		}
		dc.grabar(c);
		logger.info("grabarCasoDeUso()");
	}

}
