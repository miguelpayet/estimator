package pe.com.pps.ui.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;

@AuthorizeInstantiation("usuario")
public class HomePage extends PaginaBaseEstimador {

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(final PageParameters parameters) {
		super(parameters);
		agregarComponentes();
	}

	private void agregarComponentes() {
		add(new Label("page-title", "estimador funcional"));
		add(new Link("lista_estimaciones") {
			public void onClick() {
				throw new RestartResponseException(PaginaListaEstimaciones.class);
			}
		});
		add(new Link("nueva_estimacion") {
			public void onClick() {
				throw new RestartResponseException(PaginaEstimacion.class);
			}
		});
		add(new Link("link-logout") {
			public void onClick() {
				Subject subject = SecurityUtils.getSubject();
				SecurityUtils.getSecurityManager().logout(subject);
			}
		});
	}

}