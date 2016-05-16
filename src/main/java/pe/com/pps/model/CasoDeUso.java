package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caso_de_uso")
@IdClass(CasoDeUsoPK.class)
public class CasoDeUso implements Serializable {

	@Column(name = "complejidad")
	Integer complejidad;
	@Column(name = "descripcion")
	private String descripcion;
	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion")
	private Estimacion estimacion;
	@Id
	@Column(name = "numcaso")
	private Integer numCaso;
	@ManyToOne
	@JoinColumn(name = "idplataforma")
	private Plataforma plataforma;

	public Integer getComplejidad() {
		return complejidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Integer getNumCaso() {
		return numCaso;
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

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setNumCaso(Integer numCaso) {
		this.numCaso = numCaso;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

}
