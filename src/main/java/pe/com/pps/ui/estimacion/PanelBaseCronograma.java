package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Cronograma;
import pe.com.pps.model.Desviacion;
import pe.com.pps.model.Estimacion;

public class PanelBaseCronograma extends PanelBaseEstimacion {

	protected Cronograma cronograma;

	PanelBaseCronograma(String id, IModel<Estimacion> unModelo) {
		super(id, unModelo);
	}

	void agregarDesviacion() {
		add(new Label("desviacion", new Model<>(Desviacion.porcentaje())));
	}

	protected void init(Estimacion unaEstimacion) {
		cronograma = getEstimacion().getCronograma();
	}

}
