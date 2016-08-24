package pe.com.pps.ui.base;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.trazos.login.cabecera.PaginaBaseCabecera;
import pe.trazos.login.cabecera.PanelCabecera;

public class PaginaBaseEstimador extends PaginaBaseCabecera {

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
