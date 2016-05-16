package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import pe.com.pps.dao.HibernateUtil;

import java.util.List;

// T es la clase de la entidad que prueba la subclase que extiende TestBase
public abstract class TestBase<T> {

	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	protected static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
	protected static final String USER = "root";
	protected static final String PASSWORD = "root";
	protected IDatabaseTester databaseTester;
	private final Logger logger = LogManager.getLogger(TestBase.class);

	Session sesion;
	Transaction transaccion;

	@BeforeClass
	public static void setup() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, JDBC_DRIVER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, JDBC_URL);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, USER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, PASSWORD);
	}

	@Before
	public void abrirTransaccion() {
		logger.debug("abrir transacción");
		traerSesion();
		transaccion = sesion.beginTransaction();
	}

	@Before
	public void cargarData() throws Exception {
		IDataSet dataSet = getDataSet();
		if (dataSet != null) {
			databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
			IDatabaseConnection connection =  databaseTester.getConnection();
			DatabaseConfig config = connection.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
			databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
			databaseTester.setDataSet(dataSet);
			databaseTester.onSetup();
			logger.info("cargarData()");
		}
	}

	@After
	public void cerrarTransaccion() {
		logger.debug("commit?");
		if (transaccion != null && transaccion.getStatus().equals(TransactionStatus.ACTIVE)) {
			logger.debug("commit!");
			sesion.getTransaction().commit();
		}
	}

	protected Criteria crearCriteria(Class unaClase) {
		return sesion.createCriteria(unaClase);
	}

	@After
	public void descargarData() throws Exception {
		if (databaseTester != null) {
			databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
			databaseTester.onTearDown();
			//databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
			//databaseTester.onSetup();
			logger.info("descargarData()");
		}
	}

	abstract protected IDataSet getDataSet() throws Exception;

	protected List<T> listar(Class unaClase) {
		Criteria criterio = crearCriteria(unaClase);
		return criterio.list();
	}

	@SuppressWarnings("unchecked")
	protected T obtener(Class unaClase, String unCampo, int unId) {
		Criteria criterio = crearCriteria(unaClase);
		criterio.add(Restrictions.eq(unCampo, unId));
		return (T) criterio.uniqueResult();
	}

	public void traerSesion() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sesion = sf.getCurrentSession();
	}

}
