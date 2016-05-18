package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import pe.com.pps.dao.DaoFactor;
import pe.com.pps.dao.DaoFactorAmbiental;
import pe.com.pps.dao.DaoFactorTecnico;
import pe.com.pps.model.Factor;
import pe.com.pps.model.FactorAmbiental;
import pe.com.pps.model.FactorTecnico;

import java.io.FileInputStream;

public class TestFactor extends TestBase<Factor> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null; //new XmlDataSet(new FileInputStream("datasetFactor.xml"));
	}

	@Test
	public void probarExisteFactorTecnico() {
		logger.info("probarExisteFactorTecnico()");
		DaoFactorTecnico df = new DaoFactorTecnico();
		FactorTecnico f = df.get(1);
		Assert.assertNotNull(f);
		Assert.assertThat(f.getIdFactor(), org.hamcrest.CoreMatchers.is(1));
		Assert.assertEquals(f.getNombre(), "Sistema Distribuido");
	}

	@Test
	public void probarExisteFactorAmbiental() {
		logger.info("probarExisteFactorAmbiental()");
		DaoFactorAmbiental df = new DaoFactorAmbiental();
		FactorAmbiental f = df.get(14);
		Assert.assertNotNull(f);
		Assert.assertThat(f.getIdFactor(), org.hamcrest.CoreMatchers.is(14));
		Assert.assertEquals(f.getNombre(), "Familiaridad con el modelo del proyecto utilizado");
	}
}
