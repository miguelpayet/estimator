package pe.com.pps.model;

import pe.com.pps.ui.estimacion.PanelFilaCronograma;
import pe.com.pps.ui.estimacion.PanelFilaTareaDuracion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "4")
public class TareaDuracion extends TareaGestion {

	@Override
	public String getClaseCss() {
		return "duracion";
	}

	@Override
	public Class<? extends PanelFilaCronograma> getClasePanel() {
		return PanelFilaTareaDuracion.class;
	}

}
