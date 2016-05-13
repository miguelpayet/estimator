package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;
import pe.com.pps.model.PuntoCasoDeUso;

import java.io.FileInputStream;

public class TestPunto extends TestBase<PuntoCasoDeUso> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null;
	}

	@Test
	public void probarExistePunto() {
		logger.info("probarExistePunto()");
	}

}
