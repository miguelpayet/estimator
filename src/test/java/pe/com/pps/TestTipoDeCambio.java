package pe.com.pps;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTipoDeCambio {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/estimator";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	IDatabaseTester databaseTester;

	@BeforeClass
	public static void configurar() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, JDBC_DRIVER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, JDBC_URL);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, USER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, PASSWORD);
		System.out.println("TestTipoDeCambio.configurar()");
	}

	@Before
	public void cargarData() throws Exception {
		IDataSet dataSet = getDataSet();
		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
		System.out.println("TestTipoDeCambio.cargarData()");
	}

	@After
	public void desCargarData() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onTearDown();
	}
	
	protected IDataSet getDataSet() throws Exception {
		System.out.println("TestTipoDeCambio.getDataSet()");
		return new XmlDataSet(new FileInputStream("datasetTipoCambio.xml"));
	}

	@Test
	public void probarExisteTipo() {
		System.out.println("TestTipoDeCambio.probarExisteTipo()");
		assertThat(1, is(1));
	}

}
