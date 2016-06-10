package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// la tarea de gestión se calcula asignando un % del recurso según los días u horas recibidos

@Entity
@DiscriminatorValue(value = "3")
public class TareaGestion extends Tarea {

	public Double convertirDiasAHoras(TareaCronograma unaTarea) {
		Double horas = null;
		if (unaTarea != null) {
			horas = Util.round(unaTarea.getDias() * unaTarea.getPorcentaje() * 8, 2); // todo: horas del proveedor
		}
		return horas;
	}

	public Double convertirHorasADias(TareaCronograma unaTarea) {
		Double dias = null;
		if (unaTarea != null) {
			dias = Util.round(unaTarea.getHoras() / (unaTarea.getPorcentaje() * 8), 2); // todo: horas del proveedor
		}
		return dias;
	}

}
