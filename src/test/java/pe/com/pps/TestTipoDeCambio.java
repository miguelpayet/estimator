package pe.com.pps;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pe.com.pps.dao.HibernateUtil;
import pe.com.pps.model.TipoDeCambio;

import java.io.FileInputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
		databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
		System.out.println("TestTipoDeCambio.cargarData()");
	}

	@After
	public void desCargarData() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onTearDown();
		System.out.println("TestTipoDeCambio.desCargarData()");
	}

	protected IDataSet getDataSet() throws Exception {
		System.out.println("TestTipoDeCambio.getDataSet()");
		return new XmlDataSet(new FileInputStream("datasetTipoCambio.xml"));
	}

	@Test
	public void probarExisteTipo() {
		Session sesion = traerSesion();
		Criteria criteria = sesion.createCriteria(TipoDeCambio.class);
		List<TipoDeCambio> lista = criteria.list();
		int total = 0;
		for (TipoDeCambio tc : lista) {
			if (tc.getNombre().equals("Nuevo") || tc.getNombre().equals("Modificado")) {
				total++;
			}
		}
		assertThat(total, is(2));
		sesion.getTransaction().commit();
		System.out.println("TestTipoDeCambio.probarExisteTipo()");
	}

	@Test
	public void probarInsert() {
		Session sesion = traerSesion();
		TipoDeCambio tc = new TipoDeCambio();
		tc.setNombre("Hola");
		sesion.saveOrUpdate(tc);
		sesion.getTransaction().commit();
		System.out.println(tc.getIdTipoCambio());
		assertNotNull(tc.getIdTipoCambio());
	}

	private Session traerSesion() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		assertNotNull(sf);
		Session sesion = sf.getCurrentSession();
		assertNotNull(sesion);
		sesion.beginTransaction();
		return sesion;
	}

}
