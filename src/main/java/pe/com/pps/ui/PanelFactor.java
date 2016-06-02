package pe.com.pps.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.FactorEstimacion;

import java.util.ArrayList;
import java.util.List;

public class PanelFactor extends FormComponentPanel<FactorEstimacion> {

	public PanelFactor(String unId, IModel<FactorEstimacion> unModelo) {
		super(unId, unModelo);
		agregarCampos();
	}

	private void agregarCampos() {
		add(new Label("nombre", new Model<String>(getModelObject().getNombre())));
		add(new RadioChoice<Integer>("complejidad", new PropertyModel<Integer>(getModelObject(), "valor"), listar(getModelObject())));
		add(new Label("descripcion", new Model<String>(getModelObject().getDescripcion())));
	}

	private List<Integer> listar(FactorEstimacion unFactor) {
		ArrayList<Integer> lista = new ArrayList();
		for (int i = unFactor.getMinimo(); i <= unFactor.getMaximo(); i++) {
			lista.add(i);
		}
		return lista;
	}

}
