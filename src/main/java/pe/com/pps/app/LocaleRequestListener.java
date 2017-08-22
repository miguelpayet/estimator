package pe.com.pps.app;

import org.apache.wicket.Session;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import java.util.Locale;

/**
 * request listener para poner el Locale.ENGLISH en todos los requests
 */
public class LocaleRequestListener implements IRequestCycleListener {

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		Session.get().setLocale(Locale.ENGLISH);
	}

}
