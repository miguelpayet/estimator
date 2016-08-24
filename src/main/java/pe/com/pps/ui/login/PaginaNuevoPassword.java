package pe.com.pps.ui.login;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.password.nuevo.PanelNuevoPassword;

public class PaginaNuevoPassword extends PaginaBaseEstimador {

	public PaginaNuevoPassword() {
		this(new PageParameters());
	}

	public PaginaNuevoPassword(PageParameters unosParametros) {
		add(new PanelNuevoPassword("panel-nuevo-password", unosParametros));
	}

}
