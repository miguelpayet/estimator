package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "estimacion")
public class Estimacion implements Serializable {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<Actor> actores;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<CasoDeUso> casosDeUso;
	@Column(name = "eds")
	private String eds;
	@Column(name = "esfuerzo")
	private Float esfuerzo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	Set<FactorEstimacion> factoresEstimacion;
	@Column(name = "fechacierre")
	private Date fechaCierre;
	@Id
	@Column(name = "idestimacion", nullable = false)
	private Integer idEstimacion;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "puntos")
	private Float puntos;

	public Estimacion() {
		casosDeUso = new HashSet<>();
		actores = new HashSet<>();
		factoresEstimacion = new HashSet<>();
	}

	public void addActor(Actor unActor) {
		actores.add(unActor);
		unActor.setEstimacion(this);
		totalizarPuntos();
	}

	public void addCasoDeUso(CasoDeUso unCaso) {
		casosDeUso.add(unCaso);
		unCaso.setEstimacion(this);
		totalizarPuntos();
	}

	public void addFactorEstimacion(FactorEstimacion unFactorEstimacion) {
		factoresEstimacion.add(unFactorEstimacion);
		unFactorEstimacion.setEstimacion(this);
		totalizarPuntos();
	}

	private Float calcularFactor(Set<FactorEstimacion> unosFactores) {
		Float factor = 0f;
		for (FactorEstimacion f : unosFactores) {
			factor += (f.getValor() * f.getFactor().getPeso());
		}
		return factor;
	}

	private Float calcularFactorAmbiental() {
		Float factor = calcularFactor(getFactoresAmbientales());
		factor = 0.6f + (factor * 0.01f);
		return factor;
	}

	private Float calcularFactorTecnico() {
		Float factor = calcularFactor(getFactoresTecnicos());
		factor = 1.4f + (factor * -0.03f);
		return factor;
	}

	public Set<FactorEstimacion> extraerFactores(Integer unTipo) {
		return factoresEstimacion.stream().filter(f -> f.getFactor().getTipoFactor().equals(unTipo)).collect(Collectors.toSet());
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

	public Set<FactorEstimacion> getFactoresAmbientales() {
		return extraerFactores(TipoFactor.AMBIENTE);
	}

	public Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	public Set<FactorEstimacion> getFactoresTecnicos() {
		return extraerFactores(TipoFactor.TECNICO);
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
		totalizarPuntos();
	}

	public void setCasosDeUso(Set<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
		totalizarPuntos();
	}

	public void setEds(String eds) {
		this.eds = eds;
	}

	public void setEsfuerzo(Float esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	public void setFactoresEstimacion(Set<FactorEstimacion> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
		totalizarPuntos();
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

	private void setPuntos(Float puntos) {
		this.puntos = puntos;
	}

	private void totalizarPuntos() {
		Float puntos = 0f;
		for (Actor act : getActores()) {
			puntos += act.getPunto().getPuntos();
		}
		for (CasoDeUso cas : getCasosDeUso()) {
			puntos += cas.getPunto().getPuntos();
		}
		setPuntos(puntos * calcularFactorTecnico() * calcularFactorAmbiental());
	}

}