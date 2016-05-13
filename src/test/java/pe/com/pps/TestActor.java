package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Test;
import pe.com.pps.dao.DaoActor;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.Actor;
import pe.com.pps.model.ActorPK;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Plataforma;

public class TestActor extends TestBase<Actor> {

	private final Logger logger = LogManager.getLogger(TestActor.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null;
	}

	@Test
	public void grabarActor() {
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(1);
		Assert.assertNotNull(est);
		DaoActor da = new DaoActor();
		ActorPK pk = new ActorPK();
		pk.setNumActor(1);
		pk.setEstimacion(est);
		Actor a = da.get(pk);
		a.setComplejidad(1);
		a.setDescripcion("Prueba");
		DaoPlataforma dp = new DaoPlataforma();
		Plataforma p = dp.get(1);
		a.setPlataforma(p);
		da.grabar(a);
		logger.info("grabarActor()");
	}

}
