package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

public class PanelFilaTareaFija extends PanelFilaCronograma {

	public PanelFilaTareaFija(String id, IModel<TareaCronograma> model) {
		super(id, model);
		agregarTarea();
	}

	@Override
	protected void agregarTarea() {
		super.agregarTarea();
		getCampoPorcentaje().setEnabled(false);
		getCheckboxIncluir().setEnabled(false);
		add(new Label("cantidad-recursos", new Model<Integer>(1)));
		add(new TextField<Double>("dias", new PropertyModel<Double>(getModelObject(), "dias")));
		add(new Label("horas", new PropertyModel<Double>(getModelObject(), "horas")));
	}

}
