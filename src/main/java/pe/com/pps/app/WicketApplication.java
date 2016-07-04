package pe.com.pps.app;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import pe.com.pps.model.*;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.homepage.HomePage;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.trazos.auth.LoginShiroRealm;
import pe.trazos.auth.SesionShiro;
import pe.trazos.ui.password.cambio.PaginaCambioPassword;
import pe.trazos.dao.HibernateUtil;
import pe.trazos.ui.login.PaginaLogin;
import pe.trazos.ui.password.nuevo.PaginaNuevoPassword;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WicketApplication extends AuthenticatedWebApplication {

	static {
		List<Class> clases = new ArrayList();
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
		return HomePage.class;
	}

	/**
	 * @return la página de login de la aplicación
	 */
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return PaginaLogin.class;
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
		// request cycle listener
		IRequestCycleListener miListener;
		miListener = new MiRequestListener();
		getRequestCycleListeners().add(miListener);
		// wicket bootstrap
		Bootstrap.install(this);
		// inicialización de shiro - todo: esto debería ser automático
		// INFO  org.apache.shiro.config.IniSecurityManagerFactory - Realms have been explicitly set on the SecurityManager instance - auto-setting of realms will not occur.
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		Set realms = new HashSet();
		realms.add(new LoginShiroRealm());
		securityManager.setRealms(realms);
		SecurityUtils.setSecurityManager(securityManager);
		// montar páginas
		mountPage("/cambiopassword", PaginaCambioPassword.class);
		mountPage("/estimacion", PaginaEstimacion.class);
		mountPage("/lista", PaginaListaEstimaciones.class);
		mountPage("/login", PaginaLogin.class);
		mountPage("/nuevopassword", PaginaNuevoPassword.class);
	}

}
