package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import pe.com.pps.dao.DaoFactor;
import pe.com.pps.model.Factor;

import java.io.FileInputStream;

public class TestFactor extends TestBase<Factor> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null; //new XmlDataSet(new FileInputStream("datasetFactor.xml"));
	}

	@Test
	public void probarExisteFactor() {
		DaoFactor df = new DaoFactor();
		Factor f = df.get(1);
		Assert.assertNotNull(f);
		Assert.assertThat(f.getIdFactor(), org.hamcrest.CoreMatchers.is(1));
		Assert.assertEquals(f.getNombre(), "Sistema Distribuido");
		logger.info("probarExisteFactor()");
	}

}
