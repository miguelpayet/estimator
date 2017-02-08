package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.core.request.handler.ListenerInterfaceRequestHandler;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pe.trazos.dao.HibernateUtil;

//manejar excepciones: http://stackoverflow.com/questions/12456302/how-to-handle-exceptions-thrown-in-wicket-custom-model/12476006

public class HibernateRequestListener implements IRequestCycleListener {

	public static final Logger log = LogManager.getLogger(HibernateRequestListener.class);

	private SessionFactory sf;

	public HibernateRequestListener() {
		sf = HibernateUtil.getSessionFactory();
	}

	private void añadirCausa(StringBuilder unBuilder, Throwable unaExcepcion) {
		if (unaExcepcion.getCause() != null) {
			unBuilder.append(" - ");
			unBuilder.append(unaExcepcion.getCause().getMessage());
		}
	}

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		log.debug("onBeginRequest {}", cycle.getRequest().getOriginalUrl());
		Session sesion = sf.getCurrentSession();
		sesion.beginTransaction();
	}

	@Override
	public void onDetach(RequestCycle cycle) {
		log.trace("onDetach {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		log.debug("onEndRequest");
	}

	public IRequestHandler onException(RequestCycle cycle, Exception e) {
		log.debug("onException");
		IPageRequestHandler handler = PageRequestHandlerTracker.getLastHandler(cycle);
		if (handler != null) {
			if (handler.isPageInstanceCreated()) {
				WebPage page = (WebPage) (handler.getPage());
				StringBuilder sb = new StringBuilder(e.getMessage());
				añadirCausa(sb, e);
				añadirCausa(sb, e.getCause());
				page.error(sb.toString());
				return new RenderPageRequestHandler(new PageProvider(page), RenderPageRequestHandler.RedirectPolicy.ALWAYS_REDIRECT);
			}
		}
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception ex) {
		log.trace("onExceptionRequestHandlerResolved {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
		log.debug("onRequestHandlerExecuted {} - {}", cycle.getRequest().getOriginalUrl(), handler.getClass());
		if (sf.getCurrentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
			sf.getCurrentSession().getTransaction().commit();
		}
		if (handler.getClass().equals(ListenerInterfaceRequestHandler.class)) {
			sf.getCurrentSession().beginTransaction();
		}
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
//		log.trace("onUrlMapped {}", cycle.getRequest().getOriginalUrl());
	}

}
