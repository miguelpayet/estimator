package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class TareaGestion extends Tarea {

	public Double convertirDiasAHoras(TareaCronograma unaTarea) {
		Double horas = null;
		if (unaTarea != null) {
			horas = unaTarea.getDias() * unaTarea.getPorcentaje() * 8; // todo: horas del proveedor
		}
		return horas;
	}

	public Double convertirHorasADias(TareaCronograma unaTarea) {
		Double dias = null;
		if (unaTarea != null) {
			dias = unaTarea.getHoras() / (unaTarea.getPorcentaje() * 8); // todo: horas del proveedor
		}
		return dias;
	}

}
