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
import java.util.HashMap;
import java.util.Map;

public class TestCasoDeUso extends TestBase<Factor> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XlsDataSet(new FileInputStream("datasetCasoDeUso.xlsx"));
	}

	@Test
	public void grabarCasoDeUso() {
		logger.info("grabarCasoDeUso()");
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
	}


	@Test
	public void leerCasoDeUso() {
		logger.info("leerCasoDeUso()");
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoCasoDeUso da = new DaoCasoDeUso();
		Map<Integer, Integer> casos = new HashMap<>();
		casos.put(1, 5);
		casos.put(2, 10);
		casos.put(3, 15);
		for (Map.Entry<Integer, Integer> entry : casos.entrySet()) {
			CasoDeUsoPK pk = new CasoDeUsoPK();
			pk.setNumCaso(entry.getKey());
			pk.setEstimacion(est);
			CasoDeUso a = da.get(pk);
			Assert.assertNotNull(a);
			Assert.assertThat(a.getPunto().getPuntos(), org.hamcrest.CoreMatchers.is(entry.getValue()));
		}
	}

}
