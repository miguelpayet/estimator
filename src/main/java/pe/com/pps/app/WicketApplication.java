package pe.com.pps.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import pe.com.pps.ui.HomePage;

public class WicketApplication extends WebApplication {
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();
		IRequestCycleListener miListener;
		miListener = new MiRequestListener();
		getRequestCycleListeners().add(miListener);
	}

}
