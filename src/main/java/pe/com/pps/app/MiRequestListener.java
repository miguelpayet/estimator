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
		log.debug("onBeginRequest {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onDetach(RequestCycle cycle) {
		log.trace("onDetach {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		log.debug("onEndRequest {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		log.error("onException {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception ex) {
		log.trace("onExceptionRequestHandlerResolved {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
		log.trace("onRequestHandlerExecuted {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
		log.trace("onRequestHandlerResolved {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
		log.trace("onRequestHandlerScheduled {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {
		log.trace("onUrlMapped {}", cycle.getRequest().getOriginalUrl());
	}
}
