package pe.com.pps.app;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import pe.com.pps.auth.PaginaEstimacionAuthorizationStrategy;
import pe.com.pps.model.*;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.home.HomePage;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.dao.HibernateUtil;
import pe.trazos.login.auth.LoginRoleCheckingStrategy;
import pe.trazos.login.auth.SesionShiro;
import pe.trazos.login.dominio.Rol;
import pe.trazos.login.dominio.TokenNuevoPassword;
import pe.trazos.login.dominio.Usuario;
import pe.trazos.login.ui.login.PaginaLogin;
import pe.trazos.login.ui.password.cambio.PaginaCambioPassword;
import pe.trazos.login.ui.password.nuevo.PaginaNuevoPassword;

import java.util.ArrayList;
import java.util.List;

public class WicketApplication extends AuthenticatedWebApplication {

	static {
		List<Class> clases = new ArrayList();
		// clases del login
		clases.add(Usuario.class);
		clases.add(Rol.class);
		clases.add(TokenNuevoPassword.class);
		// clases del aplicativo
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
		// role checking strategy para autorización
		CompoundAuthorizationStrategy cps = new CompoundAuthorizationStrategy();
		cps.add(new RoleAuthorizationStrategy(new LoginRoleCheckingStrategy()));
		cps.add(new PaginaEstimacionAuthorizationStrategy());
		getSecuritySettings().setAuthorizationStrategy(cps);
		// montar páginas
		mountPage("/cambiopassword", PaginaCambioPassword.class);
		mountPage("/consulta", PaginaVistaEstimacion.class);
		mountPage("/estimacion", PaginaEstimacion.class);
		mountPage("/lista", PaginaListaEstimaciones.class);
		mountPage("/login", PaginaLogin.class);
		mountPage("/nuevopassword", PaginaNuevoPassword.class);

	}

}
