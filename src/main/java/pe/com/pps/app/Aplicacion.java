package pe.com.pps.app;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.settings.RequestLoggerSettings;
import pe.com.pps.auth.PaginaEstimacionAuthorizationStrategy;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.login.PaginaCambioPasswordEstimator;
import pe.com.pps.ui.login.PaginaLoginEstimator;
import pe.com.pps.ui.login.PaginaNuevoPasswordEstimator;
import pe.com.pps.ui.login.PaginaSolicitudNuevoPasswordEstimator;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.dao.ExcepcionRequestListener;
import pe.trazos.dao.HibernateRequestListener;
import pe.trazos.login.app.LoginWebApplication;
import pe.trazos.login.auth.LoginRoleCheckingStrategy;
import pe.trazos.login.auth.SesionShiro;
import pe.trazos.login.visita.RequestLoggerTabla;

import java.util.ArrayList;
import java.util.Collection;

public class Aplicacion extends LoginWebApplication {

	/**
	 * @return la página inicial del aplicativo
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return PaginaListaEstimaciones.class;
	}

	public String getPackageEntidades() {
		return "pe.com.pps";
	}

	/**
	 * @return la página de cambio de password
	 */
	public Class<? extends WebPage> getPaginaCambioPassword() {
		return PaginaCambioPasswordEstimator.class;
	}

	/**
	 * @return la página de login
	 */
	public Class<? extends WebPage> getPaginaLogin() {
		return getSignInPageClass();
	}

	@Override
	public Class<? extends WebPage> getPaginaNuevoPassword() {
		return PaginaNuevoPasswordEstimator.class;
	}

	@Override
	public Class<? extends WebPage> getPaginaSolicitudNuevoPassword() {
		return PaginaSolicitudNuevoPasswordEstimator.class;
	}

	/**
	 * @return la página de login de la aplicación
	 */
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return PaginaLoginEstimator.class;
	}

	/**
	 * @return la clase de sesión que trabaja con shiro
	 */
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SesionShiro.class;
	}

	/**
	 * inicializa la aplicación de wicket
	 */
	@Override
	public void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(false);
		initRequestListeners();
		initRequestLogger();
		Bootstrap.install(this); // wicket bootstrap
		initAuth(); // estrategias de autorización
		initPaginas(); // montar páginas
	}

	private void initAuth() {
		CompoundAuthorizationStrategy cps = new CompoundAuthorizationStrategy();
		cps.add(new RoleAuthorizationStrategy(new LoginRoleCheckingStrategy()));
		cps.add(new PaginaEstimacionAuthorizationStrategy());
		getSecuritySettings().setAuthorizationStrategy(cps);
	}

	private void initPaginas() {
		mountPage("/cambiopassword", PaginaCambioPasswordEstimator.class);
		mountPage("/consulta", PaginaVistaEstimacion.class);
		mountPage("/estimacion", PaginaEstimacion.class);
		mountPage("/lista", PaginaListaEstimaciones.class);
		mountPage("/login", PaginaLoginEstimator.class);
		mountPage("/nuevopassword", PaginaNuevoPasswordEstimator.class);
	}

	private void initRequestListeners() {
		Collection<IRequestCycleListener> listeners = new ArrayList<>();
		listeners.add(new ExcepcionRequestListener());
		listeners.add(new HibernateRequestListener());      // hibernate request cycle listener
		listeners.add(new LocaleRequestListener());// locale request cycle listener
		listeners.add(new PageRequestHandlerTracker()); // page request handler tracker
		for (IRequestCycleListener l : listeners) {
			getRequestCycleListeners().add(l);
		}
	}

	private void initRequestLogger() {
		RequestLoggerSettings reqLogger = Application.get().getRequestLoggerSettings();
		reqLogger.setRequestLoggerEnabled(true);
	}

	@Override
	protected IRequestLogger newRequestLogger() {
		return new RequestLoggerTabla();
	}

}
