package pe.com.pps.ui.estimacion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

public class PanelFilaTareaEsfuerzo extends PanelFilaCronograma {

	private TextField<Integer> cantidadRecursos;

	public PanelFilaTareaEsfuerzo(String id, IModel<TareaCronograma> model) {
		super(id, model);
		agregarTarea();
	}

	protected void actualizarFila(AjaxCheckBox unCheckbox, AjaxRequestTarget unTarget) {
		if (!unCheckbox.getModel().getObject()) {
			String[] dato = {"0"};
			getCampoPorcentaje().setModelValue(dato);
			getCantidadRecursos().setModelValue(dato);
		}
		getCampoPorcentaje().setEnabled(unCheckbox.getModel().getObject());
		getCantidadRecursos().setEnabled(unCheckbox.getModel().getObject());
		unTarget.add(this);
	}

	@Override
	protected void agregarTarea() {
		super.agregarTarea();
		cantidadRecursos = new TextField<>("cantidad-recursos", new PropertyModel<Integer>(getModelObject(), "recursos"));
		add(cantidadRecursos);
		add(new Label("dias", new PropertyModel<Double>(getModelObject(), "dias")));
		add(new Label("horas", new PropertyModel<Double>(getModelObject(), "horas")));
		getCampoPorcentaje().setEnabled(getCheckboxIncluir().getModelObject());
		getCantidadRecursos().setEnabled(getCheckboxIncluir().getModelObject());
	}

	public TextField<Integer> getCantidadRecursos() {
		return cantidadRecursos;
	}

}
