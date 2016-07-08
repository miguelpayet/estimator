package pe.com.pps.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authorization.strategies.page.AbstractPageAuthorizationStrategy;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.home.HomePage;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.login.dao.DaoUsuario;
import pe.trazos.login.dominio.Usuario;

/**
 * estrategia de autorización para el acceso a la página de estimación
 * -> el usuario asignado a la estimación puede modificarla
 * -> otros usuarios pasan a la página de consulta / impresión
 */
public class PaginaEstimacionAuthorizationStrategy extends AbstractPageAuthorizationStrategy {

	private static final Logger log = LogManager.getLogger(PaginaEstimacionAuthorizationStrategy.class);

	/**
	 * método que valida el acceso a la página de edición o consulta de una estimación
	 *
	 * @param pageClass -> el tipo de página que está construyendo wicket. solamente nos interesa PaginaEstimacion
	 * @param <T>       -> el tipo de página que recibimos como parámetro
	 * @return -> true o false para permitir o negar el acceso. este método siempre devuelve true, los casos donde no hay acceso los
	 * redirige al home page o a la página de consulta según el caso
	 */
	protected <T extends Page> boolean isPageAuthorized(Class<T> pageClass) {
		log.debug("autorizar {}", pageClass.toString());
		// validar el acceso a la pagina de estimacion para el eds original
		if (pageClass == PaginaEstimacion.class) {
			// obtener el usuario conectado
			Subject subject = SecurityUtils.getSubject();
			// si no hay usuario autentificado, enviarlo al home
			if (!(subject.isAuthenticated() || subject.isRemembered())) {
				log.debug("no existe usuario autenticado - autenticado: {} - recordado: {}", subject.isAuthenticated(), subject.isRemembered());
				throw new RestartResponseException(HomePage.class);
			}
			// obtener el código de estimación del request
			Request req = RequestCycle.get().getRequest();
			Url url = req.getUrl();
			Url.QueryParameter qp = url.getQueryParameter("id");
			String id = qp.getValue();
			// buscar la estimacion en la base de datos
			DaoEstimacion de = new DaoEstimacion();
			Estimacion est = de.get(Integer.valueOf(id));
			if (est == null) {
				log.debug("no existe estimación en base de datos");
				throw new RestartResponseException(HomePage.class);
			}
			// buscar el usuario en la base de datos
			DaoUsuario du = new DaoUsuario();
			Usuario u = du.getPorCodigo((String) subject.getPrincipal());
			if (u == null) {
				log.debug("no existe usuario [{}] en base de datos", subject.getPrincipal());
				throw new RestartResponseException(HomePage.class);
			}
			// compararlo con el usuario de la estimación
			if (!u.equals(est.getUsuario())) {
				log.debug("enviando a página de consulta");
				PageParameters params = new PageParameters();
				params.add(qp.getName(), qp.getValue());
				throw new RestartResponseException(PaginaVistaEstimacion.class, params);
			}
		}
		log.debug("autorizado");
		return true;
	}

}
