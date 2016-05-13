package pe.com.pps.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.model.Estimacion;

public class PaginaEstimacion extends PaginaBase {

	public PaginaEstimacion() {
		super();
		agregarTitulo(null);
	}

	public PaginaEstimacion(final PageParameters parameters) {
		super(parameters);
	}

	private void agregarTitulo(Estimacion unaEstimacion) {
		String titulo;
		if (unaEstimacion == null) {
			titulo = "nueva estimación";
		} else {
			titulo = unaEstimacion.getNombre();
		}
		add(new Label("titulo", titulo));
	}

}
