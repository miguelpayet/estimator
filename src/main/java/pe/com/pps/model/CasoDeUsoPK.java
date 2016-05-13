package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import java.io.Serializable;

public class CasoDeUsoPK implements Serializable {

	@Column(insertable = false, updatable = false)
	protected Integer idEstimacion;
	@Column(insertable = false, updatable = false)
	protected Integer numCaso;

	public CasoDeUsoPK(Integer unIdEstimacion, Integer unNumero) {
		idEstimacion = unIdEstimacion;
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
			return pk.idEstimacion == pk.getIdEstimacion() && numCaso == pk.getNumCaso();
		}
	}

	public Integer getIdEstimacion() {
		return idEstimacion;
	}

	public Integer getNumCaso() {
		return numCaso;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(idEstimacion).append(numCaso).toHashCode();
	}

	public void setIdEstimacion(Integer idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setNumCaso(Integer numCaso) {
		this.numCaso = numCaso;
	}

}
