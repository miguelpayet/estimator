package pe.com.pps.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.com.pps.dao.HibernateUtil;

import javax.servlet.*;
import java.io.IOException;

public class HibernateFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(HibernateFilter.class);

	@Override
	public void destroy() {
		log.debug("destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.getCurrentSession();
		try {
			log.debug("doFilter.beginTransaction");
			sesion.beginTransaction();
			chain.doFilter(request, response);
			log.debug("doFilter.commit");
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
			throw new ServletException(ex);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init");
	}

}