package pe.com.pps.ui;

import org.apache.wicket.markup.html.form.Button;

class BotonCrear extends Button {

	BotonCrear(String id) {
		super(id);
	}

	@Override
	public void onSubmit() {
		PaginaTabla pag = (PaginaTabla) getPage();
		pag.irPaginaEdicion();
	}

}