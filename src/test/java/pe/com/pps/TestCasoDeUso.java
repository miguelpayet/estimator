package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pe.com.pps.dao.DaoCasoDeUso;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;

import java.io.FileInputStream;

public class TestCasoDeUso extends TestBase<Factor> {

	private static IDatabaseTester databaseTester;
	private static final Logger logger = LogManager.getLogger(TestBase.class);

	@BeforeClass
	public static void cargarDataActor() throws Exception {
		databaseTester = TestBase.cargarData(new XlsDataSet(new FileInputStream("datasetCasoDeUso.xlsx")));
	}

	@AfterClass
	public static void descargarDataActor() throws Exception {
		TestBase.descargarData(databaseTester);
	}

	@Test
	public void grabarCasoDeUso() {
		logger.info("grabarCasoDeUso()");
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoCasoDeUso dc = new DaoCasoDeUso();
		CasoDeUso c = dc.get(1);
		if (c == null) {
			c = new CasoDeUso();
			c.setEstimacion(est);
			c.setComplejidad(1);
			c.setDescripcion("Hola");
			DaoPlataforma dp = new DaoPlataforma();
			Plataforma p = dp.get(1);
			c.setPlataforma(p);
		}
		dc.persistir(c);
	}

	@Test
	public void leerCasoDeUso() {
		logger.info("leerCasoDeUso()");
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoCasoDeUso da = new DaoCasoDeUso();
		int[][] casos = {{1,5},{2,10},{3,15}};
		for (int i = 0; i <= 2; i++) {
			CasoDeUso a = da.get(casos[i][0]);
			Assert.assertNotNull(a);
			Assert.assertThat(a.getPunto().getPuntos(), org.hamcrest.CoreMatchers.is(casos[i][1]));
		}
	}

}
