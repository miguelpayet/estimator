package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import pe.trazos.dao.HibernateUtil;
import pe.trazos.login.auth.LoginSecurityUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

/**
 * clase para registrar visitas en base de datos
 */
@Deprecated
public class VisitasRequestListener implements IRequestCycleListener {

	private class Grabador implements Runnable {
		public void run() {
			Session session = HibernateUtil.getCurrentSession();
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement("insert into visita (request, usuario, duracion, protocol, useragent, platform, fecha) " + "values(?, ?, ?, ?, ?, ?, ?)");
					pstmt.setString(1, (String) requestProps.get("url"));
					pstmt.setString(2, (String) requestProps.get("usuario"));
					pstmt.setLong(3, (long) requestProps.get("time"));
					pstmt.setString(4, (String) requestProps.get("protocol"));
					pstmt.setString(5, (String) requestProps.get("useragent"));
					pstmt.setString(6, (String) requestProps.get("platform"));
					pstmt.setTimestamp(7, (Timestamp) requestProps.get("fecha"));
					pstmt.execute();
				}
			});
		}
	}

	private static Logger log = LogManager.getLogger(VisitasRequestListener.class);
	Properties requestProps;

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		// propiedades al inicio del request
		requestProps = new Properties();
		requestProps.put("usuario", LoginSecurityUtil.getUsuario());
		requestProps.put("url", cycle.getRequest().getOriginalUrl().toString());
		requestProps.put("time", System.currentTimeMillis());
		requestProps.put("fecha", new Timestamp(System.currentTimeMillis()));
		try {
			ShiroHttpServletRequest x = (ShiroHttpServletRequest) cycle.getRequest().getContainerRequest();
			requestProps.put("protocol", x.getProtocol());
		} catch (ClassCastException e) {
			log.error("error al hacer cast de {} a {}", cycle.getRequest().getContainerRequest().getClass().getName(), "ShiroHttpServletRequest");
		}
		WebClientInfo ci = WebSession.get().getClientInfo();
		if (ci != null) {
			requestProps.put("useragent", ci.getUserAgent());
			ClientProperties cp = ci.getProperties();
			if (cp != null) {
				requestProps.put("platform", cp.getNavigatorPlatform() == null ? "" : cp.getNavigatorPlatform());
			}
		}
	}

	@Override
	public void onDetach(RequestCycle cycle) {
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		// propiedades al final del request
		requestProps.put("time", System.currentTimeMillis() - (long) requestProps.get("time"));
		// grabar
		try {
			Runnable job = new Grabador();
			Thread t = new Thread(job);
			t.run();
			t.join();
		} catch (InterruptedException e) {
			log.error("error al grabar visita {} - {}", e.getMessage(), e.getCause());
		}
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
