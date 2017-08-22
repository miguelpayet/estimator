package pe.com.pps.test;

//import de.agilecoders.wicket.core.WicketApplicationTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadState;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHomePage { //extends WicketApplicationTest {

	private static Logger log = LogManager.getLogger(TestHomePage.class);
	private Subject _mockSubject;
	private ThreadState _threadState;
	private Session sesion;

	/*@BeforeClass
	static public void hibernateInit() {
		List<Class> clases = LoginSecurityUtil.getClases();
		clases.add(Actor.class);
		clases.add(CasoDeUso.class);
		clases.add(Complejidad.class);
		clases.add(CostoAdicional.class);
		clases.add(Estimacion.class);
		clases.add(Factor.class);
		clases.add(FactorAmbiental.class);
		clases.add(FactorEstimacion.class);
		clases.add(FactorTecnico.class);
		clases.add(Parametro.class);
		clases.add(Plataforma.class);
		clases.add(Proveedor.class);
		clases.add(Punto.class);
		clases.add(Tarea.class);
		clases.add(TareaCronograma.class);
		clases.add(TareaCronogramaPK.class);
		clases.add(TareaDuracion.class);
		clases.add(TareaEsfuerzo.class);
		clases.add(TareaFija.class);
		clases.add(TareaGestion.class);
		clases.add(TareaSeguimiento.class);
		clases.add(TipoCosto.class);
		clases.add(TipoFactor.class);
		clases.add(TipoPunto.class);
		HibernateUtil.inicializar(clases);
	}*/

/*	@Test
	public void clases() throws IOException {
		ClassPath cp = ClassPath.from(getClass().getClassLoader());
		ImmutableSet<ClassPath.ClassInfo> clases = cp.getTopLevelClasses("pe.com.pps.model");
		for (ClassPath.ClassInfo c : clases) {
			Class cc = c.load();
			Annotation a = cc.getAnnotation(Entity.class);
			if (a != null) {

			}
			log.info(c.getName());
		}
	}*/

/*	@After
	public void hibernateTxClose() {
		sesion.getTransaction().commit();
	}*/

/*	@Before
	public void hibernateTxOpen() {
		sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
	}*/

/*	@Test
	public void homePageAutenticado() {
		when(_mockSubject.isAuthenticated()).thenReturn(true);
		tester().startPage(PaginaListaEstimaciones.class);
		tester().assertRenderedPage(PaginaListaEstimaciones.class);
	}*/

/*	@Test
	public void homePageNormal() {
		when(_mockSubject.isAuthenticated()).thenReturn(false);
		tester().startPage(PaginaListaEstimaciones.class);
		tester().assertRenderedPage(PaginaLoginEstimator.class);
		//wt.assertComponent();
	}*/

/*	@Before
	public void subject() {
		_mockSubject = Mockito.mock(Subject.class);
		_threadState = new SubjectThreadState(_mockSubject);
		_threadState.bind();
	}*/

}