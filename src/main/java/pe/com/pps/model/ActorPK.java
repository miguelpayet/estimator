package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Deprecated
@Embeddable
public class ActorPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idestimacion", insertable = false, nullable = false,  updatable = false)
	protected Estimacion estimacion;
	@Column(insertable = false, nullable = false, updatable = false)
	protected Integer numActor;

	public ActorPK() {
	}

	public ActorPK(Estimacion unIdEstimacion, Integer unNumero) {
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

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Integer getNumActor() {
		return numActor;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(43, 47).append(estimacion).append(numActor).toHashCode();
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setNumActor(Integer numActor) {
		this.numActor = numActor;
	}

}
