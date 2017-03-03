package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.wicket.protocol.http.AbstractRequestLogger;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import pe.trazos.dao.HibernateUtil;
import pe.trazos.login.auth.LoginSecurityUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

public class RequestLoggerTabla extends AbstractRequestLogger {

	private class Grabador implements Runnable {
		private WebClientInfo ci;
		private RequestData rd;
		private SessionData sd;
		private ShiroHttpServletRequest sr;

		public Grabador(RequestData unRequestData, SessionData unSessionData, WebClientInfo unClientInfo, ShiroHttpServletRequest unServletRequest) {
			ci = unClientInfo;
			rd = unRequestData;
			sd = unSessionData;
			sr = unServletRequest;
		}

		public void run() {
			Session session = HibernateUtil.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement("insert into visita (request, usuario, duracion, protocol, useragent, platform, fecha) " + "values(?, ?, ?, ?, ?, ?, ?)");
					pstmt.setString(1, (String) rd.getRequestedUrl());
					pstmt.setString(2, (String) LoginSecurityUtil.getUsuario());
					pstmt.setLong(3, (long) rd.getTimeTaken());
					pstmt.setString(4, (String) (sr == null ? "" : sr.getProtocol()));
					pstmt.setString(5, (String) (ci == null ? "" : ci.getUserAgent()));
					ClientProperties cp = ci.getProperties();
					if (cp != null) {
						pstmt.setString(6, cp.getRemoteAddress());
					} else {
						pstmt.setString(6, "");
					}
					pstmt.setTimestamp(7, new Timestamp(rd.getStartDate().getTime()));
					pstmt.execute();
				}
			});
			tx.commit();
		}
	}

	private static Logger log = LogManager.getLogger(RequestLoggerTabla.class);

	@Override
	protected void log(RequestData rd, SessionData sd) {
		ShiroHttpServletRequest sr;
		try {
			sr = (ShiroHttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
		} catch (ClassCastException e) {
			sr = null;
			log.error("error al hacer cast de {} a {}", RequestCycle.get().getRequest().getContainerRequest().getClass().getName(), "ShiroHttpServletRequest");
		}
		try {
			Runnable job = new Grabador(rd, sd, WebSession.get().getClientInfo(), sr);
			Thread t = new Thread(job);
			t.run();
			t.join();
		} catch (InterruptedException e) {
			log.error("error al grabar visita {} - {}", e.getMessage(), e.getCause());
		}
	}

}
