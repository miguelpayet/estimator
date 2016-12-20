package pe.com.pps.app;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import pe.com.pps.auth.PaginaEstimacionAuthorizationStrategy;
import pe.com.pps.model.*;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.login.PaginaCambioPasswordEstimator;
import pe.com.pps.ui.login.PaginaLoginEstimator;
import pe.com.pps.ui.login.PaginaNuevoPasswordEstimator;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.dao.HibernateUtil;
import pe.trazos.login.auth.AuthenticatedWebApplicationBase;
import pe.trazos.login.auth.LoginRoleCheckingStrategy;
import pe.trazos.login.auth.LoginSecurityUtil;
import pe.trazos.login.auth.SesionShiro;

import java.util.List;

public class WicketApplication extends AuthenticatedWebApplicationBase {

	/*lista de clases de entidad que es necesario registrar con hibernate antes de inicializarlo */
	static {
		List<Class> clases = LoginSecurityUtil.getClases();
		clases.add(Actor.class);
		clases.add(CasoDeUso.class);
		clases.add(Complejidad.class);
		clases.add(CostoAdicional.class);
		clases.add(Estimacion.class);
		clases.add(Factor.class);
		clases.add(FactorAmbiental.class);
		clases.add(FactorEstimacion.class);
		clases.add(FactorTecnico.class);
		clases.add(Parametro.class);
		clases.add(Plataforma.class);
		clases.add(Proveedor.class);
		clases.add(Punto.class);
		clases.add(Tarea.class);
		clases.add(TareaCronograma.class);
		clases.add(TareaCronogramaPK.class);
		clases.add(TareaDuracion.class);
		clases.add(TareaEsfuerzo.class);
		clases.add(TareaFija.class);
		clases.add(TareaGestion.class);
		clases.add(TareaSeguimiento.class);
		clases.add(TipoCosto.class);
		clases.add(TipoFactor.class);
		clases.add(TipoPunto.class);
		HibernateUtil.inicializar(clases);
	}

	/**
	 * @return la página inicial del aplicativo
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return PaginaListaEstimaciones.class;
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
		return PaginaLoginEstimator.class;
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
		// hibernate request cycle listener
		getRequestCycleListeners().add(new HibernateRequestListener());
		// locale request cycle listener
		getRequestCycleListeners().add(new LocaleRequestListener());
		// session listener
		//getSessionListeners().add(new EstimadorSessionListener());
		// wicket bootstrap
		Bootstrap.install(this);
		// estrategias de autorización
		CompoundAuthorizationStrategy cps = new CompoundAuthorizationStrategy();
		cps.add(new RoleAuthorizationStrategy(new LoginRoleCheckingStrategy()));
		cps.add(new PaginaEstimacionAuthorizationStrategy());
		getSecuritySettings().setAuthorizationStrategy(cps);
		// montar páginas
		mountPage("/cambiopassword", PaginaCambioPasswordEstimator.class);
		mountPage("/consulta", PaginaVistaEstimacion.class);
		mountPage("/estimacion", PaginaEstimacion.class);
		mountPage("/lista", PaginaListaEstimaciones.class);
		mountPage("/login", PaginaLoginEstimator.class);
		mountPage("/nuevopassword", PaginaNuevoPasswordEstimator.class);
	}

}
