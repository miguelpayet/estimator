package pe.com.pps.model;

import javax.persistence.*;

@Entity
@Table(name = "actor")
@IdClass(ActorPK.class)
public class Actor extends Puntuable {

	@Id
	@Column(name = "numactor")
	private Integer numActor;

	public Actor() {
	}

	public Integer getNumActor() {
		return numActor;
	}

	public Punto getPunto() {
		return super.getPunto(TipoPunto.ACTOR);
	}

	public void setNumActor(Integer numActor) {
		this.numActor = numActor;
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getSimpleName(), descripcion);
		} else {
			return super.toString();
		}
	}

}