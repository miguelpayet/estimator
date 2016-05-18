package pe.com.pps.model;

import pe.com.pps.dao.DaoPuntoCasoDeUso;

import javax.persistence.*;
import java.io.Serializable;

import static pe.com.pps.model.TipoPunto.CASO_DE_USO;

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
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Id
	@Column(name = "numcaso")
	private Integer numCaso;
	@ManyToOne
	@JoinColumn(name = "idplataforma", nullable = false)
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

	public PuntoCasoDeUso getPunto() {
		DaoPuntoCasoDeUso dp = new DaoPuntoCasoDeUso();
		PuntoCasoDeUso p = dp.get(CASO_DE_USO, getComplejidad());
		return p;
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
