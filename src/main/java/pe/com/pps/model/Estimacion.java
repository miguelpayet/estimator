package pe.com.pps.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.pps.dao.*;
import pe.trazos.login.modelo.Usuario;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "estimacion")
public class Estimacion implements Serializable {

	private final static Logger log = LogManager.getLogger(Estimacion.class);

	public static Estimacion get(Integer id) {
		DaoEstimacion de = new DaoEstimacion();
		return de.get(Integer.valueOf(id));
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<Actor> actores;
	@Transient
	private boolean actualizado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<CasoDeUso> casosDeUso;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<CostoAdicional> costosAdicionales;
	@Column(name = "eds")
	private String eds;
	@Column(name = "esfuerzo")
	private Double esfuerzo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<FactorEstimacion> factoresEstimacion;
	@Column(name = "fechacalculo")
	private Date fechaCalculo;
	@Id
	@Column(name = "idestimacion", nullable = false)
	private Integer idEstimacion;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "puntos")
	private Double puntos;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private List<TareaCronograma> tareasCronograma;
	@ManyToOne
	@JoinColumn(name = "idusuario", nullable = false)
	private Usuario usuario;
	@Version
	@Column(name = "version")
	private Integer version;

	public Estimacion() {
		actores = new HashSet<>();
		actualizado = false;
		casosDeUso = new HashSet<>();
		costosAdicionales = new HashSet<>();
		factoresEstimacion = new HashSet<>();
		tareasCronograma = new ArrayList<>();
		version = null;
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

	public void addCostoAdicional(CostoAdicional unCosto) {
		costosAdicionales.add(unCosto);
		unCosto.setEstimacion(this);
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
		tc.setIncluir(unaTarea.isIncluir());
		tareasCronograma.add(tc);
	}

	public boolean compararCon(Estimacion unaEstimacion) {
		boolean iguales;
		String[] campos = {"eds", "esfuerzo", "fechaCalculo", "idEstimacion", "nombre", "puntos", "version"};
		iguales = Comparador.comparar(Estimacion.class, this, unaEstimacion, campos);
		// todo: posiblemente comparar las listas de actores
		return iguales;
	}

	public List<FactorEstimacion> extraerFactores(Integer unTipo) {
		return new Factorama(this).extraerFactores(unTipo);
	}

	public void generarCronograma() throws ExcepcionCronograma {
		totalizar(true);
		Cronograma c = new Cronograma(this);
		c.calcular();
		fechaCalculo = new Date();
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public Set<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	public Double getCostoTotal() {
		Double costo = 0.0;
		for (TareaCronograma t : tareasCronograma) {
			costo += t.getCosto();
		}
		for (CostoAdicional c : costosAdicionales) {
			costo += Util.round(c.getCosto(), 2);
		}
		return costo;
	}

	public Set<CostoAdicional> getCostosAdicionales() {
		return costosAdicionales;
	}

	public Cronograma getCronograma() {
		return new Cronograma(this);
	}

	public String getEds() {
		return eds;
	}

	public Double getEsfuerzo() {
		totalizar();
		return Util.round(esfuerzo, 2);
	}

	/**
	 * método para wicket
	 *
	 * @return suma el total de horas del esfuerzo de las tareas del cronograma y lo formatea
	 */
	public String getEsfuerzoCronograma() {
		double total = 0d;
		for (TareaCronograma t : tareasCronograma) {
			total += t.getHoras();
		}
		return Util.format(total);
	}

	/**
	 * método para wicket
	 *
	 * @return las horas de esfuerzo de la estimación (sin gestión ni acompañamiento), en formato decimal
	 */
	@SuppressWarnings("unused")
	public String getEsfuerzoString() {
		return Util.format(getEsfuerzo());
	}

	private List<FactorEstimacion> getFactoresAmbientales() {
		return new Factorama(this).getFactoresAmbientales();
	}

	public Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	private List<FactorEstimacion> getFactoresTecnicos() {
		return new Factorama(this).getFactoresAmbientales();
	}

	public Date getFechaCalculo() {
		return fechaCalculo;
	}

	/**
	 * método para wicket
	 *
	 * @return la última fecha de grabación de la estimación, en formato de fecha
	 */
	@SuppressWarnings("unused")
	public String getFechaCalculoString() {
		Date fecha = getFechaCalculo();
		return fecha == null ? ":" : Util.format(getFechaCalculo());
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

	/**
	 * método para wicket
	 *
	 * @return el número de puntos de la estimación en formato con 2 decimales
	 */
	@SuppressWarnings("unused")
	public String getPuntosString() {
		return Util.format(getPuntos());
	}

	public List<TareaCronograma> getTareasCronograma() {
		return tareasCronograma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Integer getVersion() {
		return version;
	}

	public List<TareaCronograma> leerTareas() {
		DaoCronograma dc = new DaoCronograma();
		return dc.listar();
	}

	public void removeActor(Actor unActor) {
		if (unActor != null) {
			if (actores.contains(unActor)) {
				actores.remove(unActor);
				DaoActor da = new DaoActor();
				da.eliminar(unActor);
			}
		}
	}

	public void removeCasoDeUso(CasoDeUso unCaso) {
		if (unCaso != null) {
			if (casosDeUso.contains(unCaso)) {
				casosDeUso.remove(unCaso);
				DaoCasoDeUso dc = new DaoCasoDeUso();
				dc.eliminar(unCaso);
			}
		}
	}

	public void removeCostoAdicional(CostoAdicional unCosto) {
		if (unCosto != null) {
			if (costosAdicionales.contains(unCosto)) {
				costosAdicionales.remove(unCosto);
				DaoCostoAdicional dca = new DaoCostoAdicional();
				dca.eliminar(unCosto);
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

	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private void sumarEsfuerzo(Double unFactor) {
		esfuerzo = 0.0;
		sumarEsfuerzoParcial(getActores(), unFactor);
		sumarEsfuerzoParcial(getCasosDeUso(), unFactor);
	}

	private void sumarEsfuerzoParcial(Set<? extends Puntuable> unosPuntuables, Double unFactor) {
		for (Puntuable p : unosPuntuables) {
			Integer puntos = p.getPunto().getPuntos();
			Double productividad = p.getFactorProductividad();
			esfuerzo += Util.round(puntos * unFactor * productividad, 2);
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
		puntos = Util.round(puntos * unFactor, 2);
	}

	public void totalizar() {
		totalizar(false);
	}

	public void totalizar(Boolean unForzar) {
		if (!actualizado || unForzar) {
			Factorama f = new Factorama(this);
			Double factorAjuste = f.getFactorTecnico() * f.getFactorAmbiental();
			sumarPuntos(factorAjuste);
			sumarEsfuerzo(factorAjuste);
			actualizado = true;
		}
	}

}
