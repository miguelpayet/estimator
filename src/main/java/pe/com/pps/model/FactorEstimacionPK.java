package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class FactorEstimacionPK implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion", insertable = false, updatable = false)
	Estimacion estimacion;
	@Id
	@ManyToOne
	@JoinColumn(name = "idfactor", insertable = false, updatable = false)
	Factor factor;

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof FactorEstimacionPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			FactorEstimacionPK pk = (FactorEstimacionPK) unObjeto;
			return getEstimacion().equals(pk.getEstimacion()) && getFactor().equals(pk.getFactor());
		}
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(getEstimacion()).append(getFactor()).toHashCode();
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}

}
