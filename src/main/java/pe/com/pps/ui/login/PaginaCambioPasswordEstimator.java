package pe.com.pps.ui.login;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.login.PanelLogin;
import pe.trazos.login.ui.password.cambio.PanelCambioPassword;

public class PaginaCambioPasswordEstimator extends PaginaBaseEstimador {
	public PaginaCambioPasswordEstimator() {
		this(new PageParameters());
	}

	public PaginaCambioPasswordEstimator(PageParameters parameters) {
		super(parameters);
		agregarTitulo("cambio password estimador");
		agregarPanelLogin();
	}

	private void agregarPanelLogin() {
		add(new PanelCambioPassword("panel-cambio-password"));
	}

}
