package pe.com.pps.ui.login;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.password.PanelCambioPassword;

public class PaginaCambioPasswordEstimator extends PaginaBaseEstimador {

	public PaginaCambioPasswordEstimator() {
		this(new PageParameters());
	}

	public PaginaCambioPasswordEstimator(PageParameters parameters) {
		super(parameters);
		agregarTitulo("cambio clave estimador");
		agregarPanelLogin();
	}

	private void agregarPanelLogin() {
		PanelCambioPassword pc = new PanelCambioPassword("panel-cambio-clave");
		add(pc);
	}

}
