package pe.com.pps.app;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * clase para registrar visitas en base de datos
 */
public class VisitasRequestListener implements IRequestCycleListener {

	@Override
	public void onBeginRequest(RequestCycle cycle) {

	}

	@Override
	public void onDetach(RequestCycle cycle) {

	}

	@Override
	public void onEndRequest(RequestCycle cycle) {

	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception exception) {

	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {

	}

	@Override
	public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {

	}

	@Override
	public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {

	}

	@Override
	public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {

	}
}
