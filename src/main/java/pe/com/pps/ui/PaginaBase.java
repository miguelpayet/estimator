package pe.com.pps.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PaginaBase extends WebPage {

	public PaginaBase() {
		super();
	}

	public PaginaBase(final PageParameters parameters) {
		super(parameters);
	}

}
