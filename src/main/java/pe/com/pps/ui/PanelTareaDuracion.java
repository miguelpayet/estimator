package pe.com.pps.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

import java.util.Arrays;

public class PanelTareaDuracion extends PanelCronograma {

	public PanelTareaDuracion(String id, IModel<TareaCronograma> model) {
		super(id, model);
		agregarTarea();
	}

	protected void agregarTarea() {
		super.agregarTarea();
		add(new Label("cantidad-recursos", new Model<Integer>(1)));
		add(new Label("dias", new PropertyModel<Double>(getModelObject(), "dias")));
		add(new Label("horas", new PropertyModel<Double>(getModelObject(), "horas")));
	}

}
