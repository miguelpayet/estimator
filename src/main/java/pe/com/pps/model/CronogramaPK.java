package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CronogramaPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idestimacion", insertable = false, updatable = false)
	protected Estimacion estimacion;
	@ManyToOne
	@JoinColumn(name = "idtarea")
	private Tarea tarea;

	public CronogramaPK(Estimacion unIdEstimacion, Tarea unaTarea) {
		estimacion = unIdEstimacion;
		tarea = unaTarea;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof CronogramaPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			CronogramaPK pk = (CronogramaPK) unObjeto;
			return getEstimacion().equals(pk.getEstimacion()) && getTarea().equals(pk.getTarea());
		}
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Tarea getTarea() {
		return tarea;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(23, 31).append(getEstimacion()).append(getTarea()).toHashCode();
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}
