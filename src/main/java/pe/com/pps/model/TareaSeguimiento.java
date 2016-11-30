package pe.com.pps.model;

import pe.com.pps.ui.estimacion.PanelFilaCronograma;
import pe.com.pps.ui.estimacion.PanelFilaTareaSeguimiento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// la tarea de seguimiento existe para aumentar la duración y las horas de gestión

@Entity
@DiscriminatorValue(value = "5")
public class TareaSeguimiento extends Tarea {

	@Override
	public String getClaseCss() {
		return "seguimiento";
	}

	@Override
	public Class<? extends PanelFilaCronograma> getClasePanel() {
		return PanelFilaTareaSeguimiento.class;
	}

	@Override
	public Double convertirDiasAHoras(TareaCronograma unaTarea){
		return 0d;
	}

}
