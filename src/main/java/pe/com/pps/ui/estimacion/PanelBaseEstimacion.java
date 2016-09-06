package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import pe.com.pps.model.Estimacion;

/**
 * panel con funcionalidad base de conocer la estimación
 */
public abstract class PanelBaseEstimacion extends Panel {

	public PanelBaseEstimacion(String id, IModel<Estimacion> unModelo) {
		super(id, unModelo);
		init(getEstimacion());
	}

	protected Estimacion getEstimacion() {
		return (Estimacion) getDefaultModel().getObject();
	}

	protected abstract void init(Estimacion unaEstimacion);

}
