package pe.com.pps.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authorization.strategies.page.AbstractPageAuthorizationStrategy;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.app.Aplicacion;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.home.HomePage;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.login.usuario.Usuario;

/**
 * estrategia de autorización para el acceso a la página de estimación
 * -> el usuario asignado a la estimación puede modificarla
 * -> otros usuarios pasan a la página de consulta / impresión
 */
public class PaginaEstimacionAuthorizationStrategy extends AbstractPageAuthorizationStrategy {

	/**
	 * método que valida el acceso a la página de edición o consulta de una estimación
	 *
	 * @param pageClass -> el tipo de página que está construyendo wicket. solamente nos interesa PaginaEstimacion
	 * @param <T>       -> el tipo de página que recibimos como parámetro
	 * @return -> true o false para permitir o negar el acceso. este método siempre devuelve true, los casos donde no hay acceso los
	 * redirige al home page o a la página de consulta según el caso
	 */
	protected <T extends Page> boolean isPageAuthorized(Class<T> pageClass) {
		// validar el acceso a la pagina de estimacion para el eds original
		if (pageClass == PaginaEstimacion.class) {
			// obtener el usuario conectado
			Subject subject = SecurityUtils.getSubject();
			// si no hay usuario autentificado, enviarlo al home
			if (!(subject.isAuthenticated() || subject.isRemembered())) {
				throw new RestartResponseException(Aplicacion.get().getHomePage());
			}
			// si no hay queryparameter, es una estimación nueva
			Request req = RequestCycle.get().getRequest();
			Url.QueryParameter qp = req.getUrl().getQueryParameter("id");
			// solamente se valida las estimaciones existentes
			if (qp != null) {
				// obtener el código de estimación del request
				String id = qp.getValue();
				// buscar la estimacion en la base de datos
				Estimacion est = Estimacion.get(Integer.valueOf(id));
				if (est == null) {
					throw new RestartResponseException(HomePage.class);
				}
				// buscar el usuario en la pe.trazos.login.ui.base de datos
				Usuario u = Usuario.getActual();
				if (u == null) {
					throw new RestartResponseException(Aplicacion.get().getHomePage());
				}
				// compararlo con el usuario de la estimación
				if (!u.equals(est.getUsuario())) {
					PageParameters params = new PageParameters();
					params.add(qp.getName(), qp.getValue());
					throw new RestartResponseException(PaginaVistaEstimacion.class, params);
				}
			}
		}
		return true;
	}

}
