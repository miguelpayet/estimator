package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Assert;
import org.junit.Test;
import pe.com.pps.dao.DaoActor;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.model.Actor;
import pe.com.pps.model.ActorPK;
import pe.com.pps.model.Estimacion;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class TestActor extends TestBase<Actor> {

	private final Logger logger = LogManager.getLogger(TestActor.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XlsDataSet(new FileInputStream("datasetActor.xlsx"));
	}

	@Test
	public void leerActor() {
		logger.info("leerActor()");
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoActor da = new DaoActor();
		// actores
		Map<Integer, Integer> actores = new HashMap<>();
		actores.put(1, 1);
		actores.put(2, 2);
		actores.put(3, 3);
		for (Map.Entry<Integer, Integer> entry : actores.entrySet()) {
			ActorPK pk = new ActorPK();
			pk.setNumActor(entry.getKey());
			pk.setEstimacion(est);
			Actor a = da.get(pk);
			Assert.assertNotNull(a);
			Assert.assertThat(a.getPunto().getPuntos(), org.hamcrest.CoreMatchers.is(entry.getValue()));
		}
	}

}
