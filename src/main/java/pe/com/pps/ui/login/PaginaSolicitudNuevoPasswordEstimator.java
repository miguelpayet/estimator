package pe.com.pps.ui.login;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.password.nuevo.PanelNuevoPassword;
import pe.trazos.login.ui.password.nuevo.PanelSolicitudNuevoPassword;

public class PaginaSolicitudNuevoPasswordEstimator extends PaginaBaseEstimador {

	public PaginaSolicitudNuevoPasswordEstimator() {
		this(new PageParameters());
	}

	public PaginaSolicitudNuevoPasswordEstimator(PageParameters unosParametros) {
		agregarTitulo("solicitud nuevo password");
		add(new PanelSolicitudNuevoPassword("panel-solicitud-nuevo-password"));
	}

}
