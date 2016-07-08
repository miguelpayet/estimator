package pe.com.pps;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import pe.com.pps.app.WicketApplication;
import pe.com.pps.ui.home.HomePage;

public class TestHomePage {
	private WicketTester tester;

	@Test
	public void homepageRendersSuccessfully() {
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
	}

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}
}
