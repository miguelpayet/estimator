package pe.com.pps.error;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class PaginaError extends WebPage {

	public PaginaError(Exception unaExcepcion) {
		agregarCampos(unaExcepcion);
	}

	public void agregarCampos(Exception unaExcepcion) {
		add(new Label("el-error", unaExcepcion.getMessage()));
	}

}
