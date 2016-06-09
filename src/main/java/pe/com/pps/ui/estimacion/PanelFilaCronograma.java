package pe.com.pps.ui.estimacion;

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

	public TextField getCampoPorcentaje() {
		return campoPorcentaje;
	}

	public PanelFilaCronograma(String id) {
		super(id);
	}

	public PanelFilaCronograma(String id, IModel<TareaCronograma> model) {
		super(id, model);
	}

	protected void agregarTarea() {
		checkboxIncluir = new CheckBox("incluir", new PropertyModel<Boolean>(getModelObject(), "incluir"));
		add(checkboxIncluir);
		add(new Label("nombre", new Model<String>(getModelObject().getNombre())));
		add(new Label("proveedor", new Model<String>(getModelObject().getNombreProveedor())));
		campoPorcentaje = new TextField("porcentaje", new PropertyModel<Double>(getModelObject(), "porcentaje"));
		add(campoPorcentaje);
	}

	public CheckBox getCheckboxIncluir() {
		return checkboxIncluir;
	}

}
