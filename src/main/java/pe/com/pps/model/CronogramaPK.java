package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CronogramaPK implements Serializable {

	@Column(insertable = false, updatable = false)
	protected Integer idEstimacion;
	@Column(insertable = false, updatable = false)
	protected Integer numCronograma;

	public CronogramaPK(Integer unIdEstimacion, Integer unNumero) {
		idEstimacion = unIdEstimacion;
		numCronograma = unNumero;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof CronogramaPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			CronogramaPK pk = (CronogramaPK) unObjeto;
			return pk.idEstimacion == pk.getIdEstimacion() && numCronograma == pk.getNumCronograma();
		}
	}

	public Integer getIdEstimacion() {
		return idEstimacion;
	}

	public Integer getNumCronograma() {
		return numCronograma;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(23, 31).append(idEstimacion).append(numCronograma).toHashCode();
	}

	public void setIdEstimacion(Integer idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setNumCronograma(Integer numCronograma) {
		this.numCronograma = numCronograma;
	}

}
