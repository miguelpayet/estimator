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

	}

	@Override
	public void onDetach(RequestCycle cycle) {

	}

	@Override
	public void onEndRequest(RequestCycle cycle) {

	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		log.error("onException {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception ex) {

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
