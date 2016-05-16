package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class CasoDeUsoPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idestimacion", insertable = false, updatable = false)
	protected Estimacion estimacion;
	@Column(insertable = false, updatable = false)
	protected Integer numCaso;

	public CasoDeUsoPK() {
	}

	public CasoDeUsoPK(Estimacion unIdEstimacion, Integer unNumero) {
		estimacion = unIdEstimacion;
		numCaso = unNumero;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof CasoDeUsoPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			CasoDeUsoPK pk = (CasoDeUsoPK) unObjeto;
			return pk.estimacion == pk.getEstimacion() && numCaso == pk.getNumCaso();
		}
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Integer getNumCaso() {
		return numCaso;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(estimacion).append(numCaso).toHashCode();
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setNumCaso(Integer numCaso) {
		this.numCaso = numCaso;
	}

}
