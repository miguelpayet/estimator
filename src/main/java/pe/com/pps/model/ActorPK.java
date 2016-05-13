package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ActorPK implements Serializable {

	@Column(name = "idestimacion", insertable = false, updatable = false)
	protected Integer estimacion;
	@Column(insertable = false, updatable = false)
	protected Integer numActor;

	public ActorPK() {
	}

	public ActorPK(Integer unIdEstimacion, Integer unNumero) {
		estimacion = unIdEstimacion;
		numActor = unNumero;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof ActorPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			ActorPK pk = (ActorPK) unObjeto;
			return pk.estimacion.equals(pk.getEstimacion()) && numActor == pk.getNumActor();
		}
	}

	public Integer getEstimacion() {
		return estimacion;
	}

	public Integer getNumActor() {
		return numActor;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(estimacion).append(numActor).toHashCode();
	}

}
