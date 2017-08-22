package pe.com.pps.ui.login;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.password.PanelNuevoPassword;

public class PaginaNuevoPasswordEstimator extends PaginaBaseEstimador {

	public PaginaNuevoPasswordEstimator() {
		this(new PageParameters());
	}

	public PaginaNuevoPasswordEstimator(PageParameters unosParametros) {
		agregarTitulo("nuevo clave");
		add(new PanelNuevoPassword("panel-nuevo-clave", unosParametros));
	}

}
