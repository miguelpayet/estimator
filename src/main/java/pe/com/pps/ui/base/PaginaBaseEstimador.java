package pe.com.pps.ui.base;

import pe.trazos.login.base.PaginaBase;
import pe.trazos.login.cabecera.PanelCabecera;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.estimacion.PaginaEstimacion;

public class PaginaBaseEstimador extends PaginaBase {

	public PaginaBaseEstimador() {
		super(new PageParameters());
	}

	public PaginaBaseEstimador(final PageParameters parameters) {
		super(parameters);
	}

	protected void agregarOpciones(PanelCabecera unPanel) {
		unPanel.agregarOpcion("crear estimación", PaginaEstimacion.class);
	}

	protected void init() {
		setTitulo("estimador funcional");
	}

}
