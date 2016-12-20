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
		log.trace("onEndRequest");
		Session sesion = sf.getCurrentSession();
		try {
			sesion.getTransaction().commit();
		} catch (Throwable ex) {
			try {
				if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					sesion.getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				log.error("Could not rollback after exception!", rbEx);
				rbEx.printStackTrace();
			}
		}
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
