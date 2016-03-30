package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "funcionalidad")
public class Funcionalidad implements Serializable {

	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion")
	private Estimacion estimacion;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFuncionalidad;
	@ManyToMany
	@JoinTable(name = "funcionalidad_interfaz", joinColumns = {@JoinColumn(name = "idfuncionalidad")},
			  inverseJoinColumns = {@JoinColumn(name = "idinterfaz")})
	private List<Interfaz> interfaces;
	@ManyToMany
	@JoinTable(name = "funcionalidad_plataforma", joinColumns = {@JoinColumn(name = "idfuncionalidad")},
			  inverseJoinColumns = {@JoinColumn(name = "idplataforma")})
	private List<Plataforma> plataformas;
	private int puntajeFuncional;

	public Funcionalidad() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Estimacion getEstimacion() {
		return this.estimacion;
	}

	public int getIdFuncionalidad() {
		return this.idFuncionalidad;
	}

	public List<Plataforma> getPlataformas() {
		return this.plataformas;
	}

	public int getPuntajeFuncional() {
		return this.puntajeFuncional;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setIdFuncionalidad(int idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public void setPuntajeFuncional(int puntajeFuncional) {
		this.puntajeFuncional = puntajeFuncional;
	}

}