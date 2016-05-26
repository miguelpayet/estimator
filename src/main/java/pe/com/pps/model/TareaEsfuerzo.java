package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class TareaEsfuerzo extends Tarea {

	public Double convertirDiasAHoras(TareaCronograma unaTarea) {
		Double horas = null;
		if (unaTarea != null) {
			horas = unaTarea.getDias() * unaTarea.getRecursos() * 8; // todo: horas del proveedor
		}
		return horas;
	}

	public Double convertirHorasADias(TareaCronograma unaTarea) {
		Double dias = null;
		if (unaTarea != null) {
			dias = (unaTarea.getHoras() / unaTarea.getRecursos()) / 8; // todo: horas del proveedor
		}
		return dias;
	}

}
