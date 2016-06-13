package pe.com.pps.ui.estimacion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.TareaCronograma;

public class PanelFilaCronograma extends FormComponentPanel<TareaCronograma> {

	private TextField campoPorcentaje;
	private CheckBox checkboxIncluir;

	public PanelFilaCronograma(String id) {
		super(id);
	}

	public PanelFilaCronograma(String id, IModel<TareaCronograma> model) {
		super(id, model);
	}

	protected void actualizarFila(AjaxCheckBox unCheckbox, AjaxRequestTarget unTarget) {

	}

	protected void agregarTarea() {
		checkboxIncluir = new AjaxCheckBox("incluir", new PropertyModel<>(getModelObject(), "incluir")) {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				actualizarFila(this, target);
			}

		};
		add(checkboxIncluir);
		add(new Label("nombre", new Model<>(getModelObject().getNombre())));
		add(new Label("proveedor", new Model<>(getModelObject().getNombreProveedor())));
		campoPorcentaje = new TextField<>("porcentaje", new PropertyModel<Double>(getModelObject(), "porcentaje"));
		add(campoPorcentaje);
	}

	public TextField getCampoPorcentaje() {
		return campoPorcentaje;
	}

	public CheckBox getCheckboxIncluir() {
		return checkboxIncluir;
	}

}
