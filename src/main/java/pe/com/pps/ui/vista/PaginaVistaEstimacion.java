package pe.com.pps.ui.vista;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBase;
import pe.com.pps.ui.base.PaginaBaseEstimacion;

public class PaginaVistaEstimacion extends PaginaBaseEstimacion {

	public PaginaVistaEstimacion() {
		this(new PageParameters());
	}

	public PaginaVistaEstimacion(PageParameters unosParametros) {
		super(unosParametros);
		agregarTitulo();
		agregarEstimacion();
	}

	private void agregarEstimacion() {
		add(new Label("id-estimacion", getEstimacion().getIdEstimacion()));
		add(new Label("nombre-estimacion", getEstimacion().getNombre()));
		add(new Label("eds-estimacion", getEstimacion().getEds()));
	}
}
