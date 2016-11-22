package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pe.trazos.dao.HibernateUtil;

//manejar excepciones: http://stackoverflow.com/questions/12456302/how-to-handle-exceptions-thrown-in-wicket-custom-model/12476006

public class HibernateRequestListener implements IRequestCycleListener {

	public static final Logger log = LogManager.getLogger(HibernateRequestListener.class);

	SessionFactory sf;

	public HibernateRequestListener() {
		sf = HibernateUtil.getSessionFactory();
	}

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		log.debug("HibernateRequestListener.onBeginRequest {}", cycle.getRequest().getOriginalUrl());
		Session sesion = sf.getCurrentSession();
		sesion.beginTransaction();
	}

	@Override
	public void onDetach(RequestCycle cycle) {
		log.trace("HibernateRequestListener.onDetach {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		log.debug("HibernateRequestListener.onEndRequest {}", cycle.getRequest().getOriginalUrl());
		Session sesion = sf.getCurrentSession();
		if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
			log.debug("rolling back transaction");
			sesion.getTransaction().rollback();
		}
	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		log.error("HibernateRequestListener.onException {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
		return null;
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception ex) {
		log.trace("MiRequestListener.onExceptionRequestHandlerResolved {} -> {}", cycle.getRequest().getOriginalUrl(), ex.getMessage());
	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
		log.trace("MiRequestListener.onRequestHandlerExecuted {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
		log.trace("MiRequestListener.onRequestHandlerResolved {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
		log.trace("MiRequestListener.onRequestHandlerScheduled {}", cycle.getRequest().getOriginalUrl());
	}

	@Override
	public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {
		log.trace("MiRequestListener.onUrlMapped {}", cycle.getRequest().getOriginalUrl());
	}

}
