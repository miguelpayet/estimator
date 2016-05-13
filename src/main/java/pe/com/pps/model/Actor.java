package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "actor")
@IdClass(ActorPK.class)
public class Actor implements Serializable {

	@Column(name = "complejidad")
	private Integer complejidad;
	@Column(name = "descripcion")
	private String descripcion;
	@Id
//	@ManyToOne
//	@JoinColumn(name = "idestimacion", referencedColumnName = "idestimacion")
	@Column(name = "idestimacion")
	private Integer estimacion;
	@Id
	@Column(name = "numactor")
	private Integer numActor;
	@ManyToOne
	@JoinColumn(name = "idplataforma")
	private Plataforma plataforma;

	public Actor() {
	}

	public Integer getComplejidad() {
		return complejidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getEstimacion() {
		return estimacion;
	}

	public Integer getNumActor() {
		return numActor;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstimacion(Integer estimacion) {
		this.estimacion = estimacion;
	}

	public void setNumActor(Integer numActor) {
		this.numActor = numActor;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getName(), descripcion);
		} else {
			return super.toString();
		}
	}

}