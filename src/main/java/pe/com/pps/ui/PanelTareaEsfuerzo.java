package pe.com.pps.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

import java.util.Arrays;

public class PanelTareaEsfuerzo extends PanelCronograma {

	public PanelTareaEsfuerzo(String id, IModel<TareaCronograma> model) {
		super(id, model);
		agregarTarea();
	}

	@Override
	protected void agregarTarea() {
		super.agregarTarea();
		add(new TextField<Integer>("cantidad-recursos", new PropertyModel<Integer>(getModelObject(), "recursos")));
		add(new Label("dias", new PropertyModel<Double>(getModelObject(), "dias")));
		add(new TextField<Double>("horas", new PropertyModel<Double>(getModelObject(), "horas")));
	}

}
