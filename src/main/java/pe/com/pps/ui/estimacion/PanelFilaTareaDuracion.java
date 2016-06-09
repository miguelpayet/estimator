package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

public class PanelFilaTareaDuracion extends PanelFilaCronograma {

	public PanelFilaTareaDuracion(String id, IModel<TareaCronograma> model) {
		super(id, model);
		agregarTarea();
	}

	protected void agregarTarea() {
		super.agregarTarea();
		getCheckboxIncluir().setEnabled(false);
		add(new Label("cantidad-recursos", new Model<Integer>(1)));
		add(new Label("dias", new PropertyModel<Double>(getModelObject(), "dias")));
		add(new Label("horas", new PropertyModel<Double>(getModelObject(), "horas")));
	}

}
