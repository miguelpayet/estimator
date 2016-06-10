package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.Cronograma;

public class PanelFilaTotal extends Panel {

	public PanelFilaTotal(String id, IModel<Cronograma> model) {
		super(id, model);
		agregarTotales();
	}

	protected void agregarTotales() {
		add(new Label("dias", new PropertyModel<Double>(getDefaultModel(), "totalDias")));
		add(new Label("horas", new PropertyModel<Double>(getDefaultModel(), "totalHoras")));
	}

}
