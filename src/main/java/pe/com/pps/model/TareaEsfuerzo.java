package pe.com.pps.model;

import pe.com.pps.ui.estimacion.PanelFilaCronograma;
import pe.com.pps.ui.estimacion.PanelFilaTareaEsfuerzo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


// la tarea de esfuerzo calcula la duración según la cantidad de recursos asignados / lo mismo para las horas pero al revés

@Entity
@DiscriminatorValue(value = "1")
public class TareaEsfuerzo extends Tarea {

	public Double convertirDiasAHoras(TareaCronograma unaTarea) {
		Double horas = null;
		if (unaTarea != null) {
			horas = Util.round(unaTarea.getDias() * unaTarea.getRecursos() * 8, 2); // todo: horas del proveedor
		}
		return horas;
	}

	public Double convertirHorasADias(TareaCronograma unaTarea) {
		Double dias = null;
		if (unaTarea != null) {
			dias = Util.round((unaTarea.getHoras() / unaTarea.getRecursos()) / 8, 2); // todo: horas del proveedor
		}
		return dias;
	}

	@Override
	public String getClaseCss() {
		return "esfuerzo";
	}

	@Override
	public Class<? extends PanelFilaCronograma> getClasePanel() {
		return PanelFilaTareaEsfuerzo.class;
	}

}
