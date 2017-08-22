package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.Estimacion;

public class PanelResumen extends PanelBaseCronograma {

	public PanelResumen(String id, IModel<Estimacion> unModelo) {
		super(id, unModelo);
		agregarDesviacion();
		agregarCampos();
	}

	private void agregarCampos() {
		add(new Label("desviacion-maximo", new PropertyModel<Double>(cronograma, "rangoMaximo")));
		add(new Label("desviacion-minimo", new PropertyModel<Double>(cronograma, "rangoMinimo")));
		add(new Label("horas-esfuerzo", new PropertyModel<Double>(getEstimacion(), "esfuerzo")));
		add(new Label("meses-cronograma", new PropertyModel<Double>(cronograma, "totalMeses")));
		add(new Label("puntos-ajustados", new PropertyModel<Double>(getEstimacion(), "puntos")));
	}

}
