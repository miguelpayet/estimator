package pe.com.pps;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import pe.com.pps.app.WicketApplication;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.homepage.HomePage;

public class TestPaginaEstimacion {
	private WicketTester tester;

	@Test
	public void estimacionRendersSuccessfully() {
		tester.startPage(PaginaEstimacion.class);
		tester.assertRenderedPage(PaginaEstimacion.class);
	}

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}
}
