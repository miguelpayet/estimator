package pe.com.pps.ui.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PaginaBase extends WebPage {

	public PaginaBase() {
		super();
	}

	public PaginaBase(final PageParameters parameters) {
		super(parameters);
	}

	protected void agregarTitulo(String unTitulo) {
		add(new Label("page-title", unTitulo));
	}

}
