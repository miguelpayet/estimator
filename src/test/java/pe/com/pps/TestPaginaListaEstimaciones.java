package pe.com.pps;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import pe.com.pps.app.WicketApplication;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;

public class TestPaginaListaEstimaciones {
	private WicketTester tester;

	@Test
	public void estimacionRendersSuccessfully() {
		tester.startPage(PaginaListaEstimaciones.class);
		tester.assertRenderedPage(PaginaListaEstimaciones.class);
	}

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}
}
