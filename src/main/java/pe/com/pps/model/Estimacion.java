package pe.com.pps.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "estimacion")
public class Estimacion implements Serializable {

	private final static Logger log = LogManager.getLogger(Estimacion.class);

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<Actor> actores;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<CasoDeUso> casosDeUso;
	@Column(name = "eds")
	private String eds;
	@Column(name = "esfuerzo")
	private Double esfuerzo;
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
	private Double puntos;

	public Estimacion() {
		casosDeUso = new HashSet<>();
		actores = new HashSet<>();
		factoresEstimacion = new HashSet<>();
	}

	public void addActor(Actor unActor) {
		actores.add(unActor);
		unActor.setEstimacion(this);
		totalizar();
	}

	public void addCasoDeUso(CasoDeUso unCaso) {
		casosDeUso.add(unCaso);
		unCaso.setEstimacion(this);
		totalizar();
	}

	public void addFactorEstimacion(FactorEstimacion unFactorEstimacion) {
		factoresEstimacion.add(unFactorEstimacion);
		unFactorEstimacion.setEstimacion(this);
		totalizar();
	}

	private Double calcularFactor(Set<FactorEstimacion> unosFactores) {
		Double factor = 0.0;
		for (FactorEstimacion f : unosFactores) {
			log.trace(String.format("%s - valor %s - peso %s - factor %s", f.getFactor().getNombre(), f.getValor(), f.getFactor().getPeso(), f.getValor() * f.getFactor().getPeso()));
			factor += (f.getValor() * f.getFactor().getPeso());
		}
		log.debug("factor de complejidad " + factor);
		return factor;
	}

	private Double calcularFactorAmbiental() {
		Double factor = calcularFactor(getFactoresAmbientales());
		factor = 1.4 + (factor * -0.03);
		log.debug(String.format("estimación %s - factor ambiental %s", getIdEstimacion(), factor));
		return factor;
	}

	private Double calcularFactorTecnico() {
		Double factor = calcularFactor(getFactoresTecnicos());
		factor = 0.6 + (factor * 0.01);
		log.debug(String.format("estimación %s - factor técnico %s", getIdEstimacion(), factor));
		return factor;
	}

	private Set<FactorEstimacion> extraerFactores(Integer unTipo) {
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

	public Double getEsfuerzo() {
		return esfuerzo;
	}

	private Set<FactorEstimacion> getFactoresAmbientales() {
		return extraerFactores(TipoFactor.AMBIENTE);
	}

	public Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	private Set<FactorEstimacion> getFactoresTecnicos() {
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

	public Double getPuntos() {
		return puntos;
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
		totalizar();
	}

	public void setCasosDeUso(Set<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
		totalizar();
	}

	public void setEds(String eds) {
		this.eds = eds;
	}

	public void setEsfuerzo(Double esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	public void setFactoresEstimacion(Set<FactorEstimacion> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
		totalizar();
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

	private void setPuntos(Double puntos) {
		this.puntos = puntos;
	}

	private void sumarEsfuerzo(Double unFactor) {
		esfuerzo = 0.0;
		sumarEsfuerzoParcial(getActores(), unFactor);
		sumarEsfuerzoParcial(getCasosDeUso(), unFactor);
	}

	private void sumarEsfuerzoParcial(Set<? extends Puntuable> unosPuntuables, Double unFactor) {
		for (Puntuable p : unosPuntuables) {
			Integer puntos = p.getPunto().getPuntos();
			Double productividad = p.getPlataforma().getFactorProductividad();
			esfuerzo += puntos * unFactor * productividad;
		}
	}

	private void sumarPuntos(Double unFactor) {
		puntos = 0.0;
		for (Actor act : getActores()) {
			puntos += act.getPunto().getPuntos();
		}
		for (CasoDeUso cas : getCasosDeUso()) {
			puntos += cas.getPunto().getPuntos();
		}
		log.debug(String.format("estimación %s - puntos antes de ajuste %s", getIdEstimacion(), puntos));
		puntos = puntos * unFactor;
		log.debug(String.format("estimación %s - puntos ajustados %s", getIdEstimacion(), puntos));
	}

	public void totalizar() {
		Double factorAjuste = calcularFactorTecnico() * calcularFactorAmbiental();
		sumarPuntos(factorAjuste);
		sumarEsfuerzo(factorAjuste);
	}

}