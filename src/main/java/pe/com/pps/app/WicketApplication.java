package pe.com.pps.app;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import pe.com.pps.ui.homepage.HomePage;

public class WicketApplication extends WebApplication {
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();
		// request cycle listener
		IRequestCycleListener miListener;
		miListener = new MiRequestListener();
		getRequestCycleListeners().add(miListener);
		// wicket bootstrap
		Bootstrap.install(this);
	}

}
