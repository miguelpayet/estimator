package pe.com.pps.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.pps.dao.DaoCronograma;

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
	@Transient
	private boolean actualizado;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private List<TareaCronograma> tareasCronograma;

	public Estimacion() {
		actualizado = false;
		casosDeUso = new HashSet<>();
		actores = new HashSet<>();
		factoresEstimacion = new HashSet<>();
		tareasCronograma = new ArrayList<>();
	}

	public void addActor(Actor unActor) {
		actores.add(unActor);
		unActor.setEstimacion(this);
		actualizado = false;
	}

	public void addCasoDeUso(CasoDeUso unCaso) {
		casosDeUso.add(unCaso);
		unCaso.setEstimacion(this);
		actualizado = false;
	}

	public void addFactorEstimacion(FactorEstimacion unFactorEstimacion) {
		factoresEstimacion.add(unFactorEstimacion);
		unFactorEstimacion.setEstimacion(this);
		actualizado = false;
	}

	public void addTareaCronograma(Tarea unaTarea) {
		TareaCronograma tc = new TareaCronograma();
		tc.setEstimacion(this);
		tc.setTarea(unaTarea);
		tareasCronograma.add(tc);
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

	public void generarCronograma() throws ExcepcionCronograma {
		Cronograma c = new Cronograma(this);
		c.calcular();
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public Set<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	public Cronograma getCronograma() {
		return new Cronograma(this);
	}

	public String getEds() {
		return eds;
	}

	public Double getEsfuerzo() {
		totalizar();
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

	public Integer getIdEstimacion() {
		return idEstimacion;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPuntos() {
		totalizar();
		return puntos;
	}

	public List<TareaCronograma> getTareasCronograma() {
		return tareasCronograma;
	}

	public List<TareaCronograma> leerTareas() {
		DaoCronograma dc = new DaoCronograma();
		return dc.listar();
	}

	public void removeActor(Actor unActor) {
		if (unActor != null) {
			if (actores.contains(unActor)) {
				actores.remove(unActor);
				unActor.setEstimacion(null);
			}
		}
	}

	public void removeCasoDeUso(CasoDeUso unCaso) {
		if (unCaso != null) {
			if (casosDeUso.contains(unCaso)) {
				casosDeUso.remove(unCaso);
				unCaso.setEstimacion(null);
			}
		}
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
		actualizado = false;
	}

	public void setCasosDeUso(Set<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
		actualizado = false;
	}

	public void setEds(String eds) {
		this.eds = eds;
	}

	public void setEsfuerzo(Double esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	public void setFactoresEstimacion(Set<FactorEstimacion> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
		actualizado = false;
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

	public void setTareasCronograma(List<TareaCronograma> tareasCronograma) {
		this.tareasCronograma = tareasCronograma;
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
		if (!actualizado) {
			Double factorAjuste = calcularFactorTecnico() * calcularFactorAmbiental();
			sumarPuntos(factorAjuste);
			sumarEsfuerzo(factorAjuste);
			actualizado = true;
		}
	}

}