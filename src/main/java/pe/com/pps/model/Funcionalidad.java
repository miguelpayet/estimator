package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the funcionalidad database table.
 * 
 */
@Entity
@NamedQuery(name="Funcionalidad.findAll", query="SELECT f FROM Funcionalidad f")
public class Funcionalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idfuncionalidad;

	private String descripcion;

	private int puntajefuncional;

	//bi-directional many-to-one association to Estimacion
	@ManyToOne
	@JoinColumn(name="idestimacion")
	private Estimacion estimacion;

	//bi-directional many-to-many association to Plataforma
	@ManyToMany
	@JoinTable(
		name="funcionalidad_plataforma"
		, joinColumns={
			@JoinColumn(name="idfuncionalidad")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idplataforma")
			}
		)
	private List<Plataforma> plataformas;

	public Funcionalidad() {
	}

	public int getIdfuncionalidad() {
		return this.idfuncionalidad;
	}

	public void setIdfuncionalidad(int idfuncionalidad) {
		this.idfuncionalidad = idfuncionalidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPuntajefuncional() {
		return this.puntajefuncional;
	}

	public void setPuntajefuncional(int puntajefuncional) {
		this.puntajefuncional = puntajefuncional;
	}

	public Estimacion getEstimacion() {
		return this.estimacion;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public List<Plataforma> getPlataformas() {
		return this.plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

}