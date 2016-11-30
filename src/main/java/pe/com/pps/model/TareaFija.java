package pe.com.pps.model;

import pe.com.pps.ui.estimacion.PanelFilaCronograma;
import pe.com.pps.ui.estimacion.PanelFilaTareaFija;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class TareaFija extends Tarea {

	@Override
	public String getClaseCss() {
		return "fija";
	}

	@Override
	public Class<? extends PanelFilaCronograma> getClasePanel() {
		return PanelFilaTareaFija.class;
	}

}
