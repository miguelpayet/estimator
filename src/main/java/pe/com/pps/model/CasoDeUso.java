package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "casodeuso")
public class CasoDeUso implements Serializable {

	@Column(name = "complejidad")
	Integer complejidad;
	@Column(name = "descripcion")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion")
	private Estimacion estimacion;
	@EmbeddedId
	private CasoDeUsoPK id;
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

	public CasoDeUsoPK getId() {
		return id;
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

	public void setId(CasoDeUsoPK id) {
		this.id = id;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

}
