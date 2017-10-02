package pe.com.pps.ui.base;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.trazos.login.auth.LoginSecurityUtil;
import pe.trazos.login.ui.base.PaginaBase;
import pe.trazos.login.ui.cabecera.PaginaBaseCabecera;
import pe.trazos.login.ui.cabecera.PanelCabecera;

public class PaginaBaseEstimador extends PaginaBaseCabecera {

	public PaginaBaseEstimador() {
		super(new PageParameters());
	}

	public PaginaBaseEstimador(final PageParameters parameters) {
		super(parameters);
	}

	protected void agregarOpciones(PanelCabecera unPanel) {
		if (LoginSecurityUtil.isAutenticado()) {
			unPanel.agregarOpcion("crear estimación", PaginaEstimacion.class);
		}
	}

	protected void init() {
		// método vacío
	}

}
