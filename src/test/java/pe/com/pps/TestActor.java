package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pe.com.pps.dao.DaoActor;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.Actor;
import pe.com.pps.model.Estimacion;

import java.io.FileInputStream;

public class TestActor extends TestBase<Actor> {

	private static final int ACTOR = 10012;
	private static IDatabaseTester databaseTester;
	private static final Logger logger = LogManager.getLogger(TestActor.class);

	@BeforeClass
	public static void cargarDataActor() throws Exception {
		databaseTester = TestBase.cargarData(new XlsDataSet(new FileInputStream("datasetActor.xlsx")));
	}

	@AfterClass
	public static void descargarDataActor() throws Exception {
		TestBase.descargarData(databaseTester);
	}

	//@Test
	public void crearActor() {
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(ACTOR);
		Assert.assertNotNull(est);
		DaoActor da = new DaoActor();
		Actor a = new Actor();
		a.setComplejidad(1);
		a.setDescripcion("hola");
		DaoPlataforma dp = new DaoPlataforma();
		est.addActor(a);
		de.persistir(est);
	}

	//@Test
	public void leerActor() {
		logger.info("leerActor()");
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(ACTOR);
		Assert.assertNotNull(est);
		DaoActor da = new DaoActor();
		// actores
		int[] actores = {1,2,3};
		for (int i = 0; i <= 2; i++) {
			Actor a = da.get(actores[i]);
			Assert.assertNotNull(a);
			Assert.assertThat(a.getPunto().getPuntos(), org.hamcrest.CoreMatchers.is(actores[i]));
		}
	}

}
