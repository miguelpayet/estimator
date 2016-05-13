package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable

public class FactorEstimacionPK implements Serializable {

	@Column(insertable = false, updatable = false)
	private Integer idEstimacion;
	@Column(insertable = false, updatable = false)
	private Integer idFactor;

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof FactorEstimacionPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			FactorEstimacionPK pk = (FactorEstimacionPK) unObjeto;
			return pk.idEstimacion == pk.getIdEstimacion() && idFactor == pk.getIdFactor();
		}
	}

	public Integer getIdEstimacion() {
		return idEstimacion;
	}

	public Integer getIdFactor() {
		return idFactor;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(idEstimacion).append(idFactor).toHashCode();
	}

	public void setIdEstimacion(Integer idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setIdFactor(Integer idFactor) {
		this.idFactor = idFactor;
	}

}
