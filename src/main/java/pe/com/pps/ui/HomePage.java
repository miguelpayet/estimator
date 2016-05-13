package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends PaginaBase {

	private final Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(final PageParameters parameters) {
		super(parameters);
		agregarComponentes();
	}

	private void agregarComponentes() {
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