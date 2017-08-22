package pe.com.pps.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * filtro para configurar la transacción de hibernate en cada request
 * crea la transacción al inicio del request
 * hace commit y destruye la transacción al final del request
 */
@Deprecated
public class HibernateFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(HibernateFilter.class);

	/**
	 * destruye el filtro
	 */
	@Override
	public void destroy() {

	}

	/**
	 * crea la transacción, ejecuta el resto de la cadena de filtros y cuando vuelve hace commit a la transacción
	 *
	 * @param request  -> el request que se está procesando
	 * @param response -> el response que se va a enviar
	 * @param chain    -> cadena de filtros en ejecución
	 * @throws IOException      -> excepción de input/output
	 * @throws ServletException -> excepción de tipo servlet
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.getCurrentSession();
		try {

			sesion.beginTransaction();
			chain.doFilter(request, response);

			sesion.getTransaction().commit();
		} catch (Throwable ex) {
			try {
				if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					sesion.getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				log.error("Could not rollback after exception!", rbEx);

			}
			throw new ServletException(ex);
		}
		*/
	}

	// inicializa el filtro
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}