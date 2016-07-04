package pe.com.pps.ui.homepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.componentes.PaginaBase;

@AuthorizeInstantiation("usuario")
public class HomePage extends PaginaBase {

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(final PageParameters parameters) {
		super(parameters);
		agregarComponentes();
	}

	private void agregarComponentes() {
		add(new Label("page-title", "estimador funcional"));
		add(new Link("lista_estimaciones") {
			public void onClick() {
				logger.info("listado de estimaciones");
				throw new RestartResponseException(PaginaListaEstimaciones.class);
			}
		});
		add(new Link("nueva_estimacion") {
			public void onClick() {
				logger.info("nueva estimación");
				throw new RestartResponseException(PaginaEstimacion.class);
			}
		});
	}

}