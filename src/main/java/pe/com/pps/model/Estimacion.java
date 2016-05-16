package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estimacion")
public class Estimacion implements Serializable {

	@PrePersist
	private void crearPK() {}
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idestimacion")
	private Set<Actor> actores;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idestimacion")
	private Set<CasoDeUso> casosDeUso;
	@Column(name = "eds")
	private String eds;
	@Column(name = "esfuerzo")
	private Float esfuerzo;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idestimacion")
	Set<FactorEstimacion> factoresEstimacion;
	@Column(name = "fechacierre")
	private Date fechaCierre;
	@Id
	@Column(name = "idestimacion")
	private Integer idEstimacion;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "puntos")
	private Float puntos;

	public Estimacion() {
		casosDeUso = new HashSet<CasoDeUso>();
		actores = new HashSet<Actor>();
		factoresEstimacion = new HashSet<FactorEstimacion>();
	}

	public void addActor(Actor unActor) {
		actores.add(unActor);
		unActor.setEstimacion(this);
	}

	public void addCasoDeUso(CasoDeUso unCaso) {
		casosDeUso.add(unCaso);
		unCaso.setEstimacion(this);
	}

	public void addFactorEstimacion(FactorEstimacion unFactorEstimacion) {
		factoresEstimacion.add(unFactorEstimacion);
		unFactorEstimacion.setEstimacion(this);
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public Set<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	public String getEds() {
		return eds;
	}

	public Float getEsfuerzo() {
		return esfuerzo;
	}

	public Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public int getIdEstimacion() {
		return idEstimacion;
	}

	public String getNombre() {
		return nombre;
	}

	public Float getPuntos() {
		return puntos;
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
	}

	public void setCasosDeUso(Set<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public void setEds(String eds) {
		this.eds = eds;
	}

	public void setEsfuerzo(Float esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	public void setFactoresEstimacion(Set<FactorEstimacion> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public void setIdEstimacion(Integer idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntos(Float puntos) {
		this.puntos = puntos;
	}

}