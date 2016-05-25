package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class MiRequestListener implements IRequestCycleListener {

	public static final Logger log = LogManager.getLogger(MiRequestListener.class);

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		log.debug("onBeginRequest");
	}

	@Override
	public void onDetach(RequestCycle cycle) {
		log.debug("onDetach");
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		log.debug("onEndRequest");
	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		log.debug("onException");
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception exception) {
		log.debug("onExceptionRequestHandlerResolved");
	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
		log.debug("onRequestHandlerExecuted");
	}

	@Override
	public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
		log.debug("onRequestHandlerResolved");

	}

	@Override
	public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
		log.debug("onRequestHandlerScheduled");
	}

	@Override
	public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {
		log.debug("onUrlMapped");
	}
}
