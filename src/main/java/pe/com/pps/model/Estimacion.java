package pe.com.pps.model;

import pe.com.pps.dao.DaoEstimacion;
import pe.trazos.dao.entidad.Entidad;
import pe.trazos.dao.factory.DaoFactory;
import pe.trazos.login.entidad.Usuario;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "estimacion")
public class Estimacion extends Entidad<Integer> {

	public static Estimacion get(Integer id) {
		DaoEstimacion de = DaoFactory.getInstance().crearDao(Estimacion.class, DaoEstimacion.class);
		return de.get(id);
	}

	public static void grabar(Estimacion unaEstimacion) throws ExcepcionCronograma {
		DaoEstimacion de = DaoFactory.getInstance().crearDao(Estimacion.class, DaoEstimacion.class);
		unaEstimacion.generarCronograma();
		de.grabar(unaEstimacion);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion", orphanRemoval = true)
	private Set<Actor> actores;
	@Transient
	private boolean actualizado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion", orphanRemoval = true)
	private Set<CasoDeUso> casosDeUso;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion", orphanRemoval = true)
	private Set<CostoAdicional> costosAdicionales;
	@Column(name = "eds")
	private String eds;
	@Column(name = "esfuerzo")
	private Double esfuerzo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estimacion")
	private Set<FactorEstimacion> factoresEstimacion;
	@Column(name = "numfase")
	private Integer fase;
	@Column(name = "fechacalculo")
	private Date fechaCalculo;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestimacion", nullable = false)
	private Integer idEstimacion;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "numestimacion")
	private Integer numero;
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

	void addFactorEstimacion(FactorEstimacion unFactorEstimacion) {
		factoresEstimacion.add(unFactorEstimacion);
		unFactorEstimacion.setEstimacion(this);
		actualizado = false;
	}

	void addTareaCronograma(Tarea unaTarea) {
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

	/**
	 * metodo para wicket
	 *
	 * @return el costo total de la estimación
	 */

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

	public String getEsfuerzoString() {
		return Util.format(getEsfuerzo());
	}

	Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	/**
	 * método para wicket
	 *
	 * @return fase de la estimación
	 */

	public Integer getFase() {
		return fase;
	}

	/**
	 * @return fecha de última grabación de la estimación
	 */

	private Date getFechaCalculo() {
		return fechaCalculo;
	}

	/**
	 * método para wicket
	 *
	 * @return la última fecha de grabación de la estimación, en formato de fecha
	 */

	public String getFechaCalculoString() {
		Date fecha = getFechaCalculo();
		return fecha == null ? "?" : Util.format(getFechaCalculo());
	}

	@Override
	public Integer getId() {
		return idEstimacion;
	}

	public Integer getIdEstimacion() {
		return idEstimacion;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * método para wicket
	 *
	 * @return el número de la estimación
	 */

	public Integer getNumero() {
		return numero;
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

	public void removeActor(Actor unActor) {
		if (unActor != null) {
			if (actores.contains(unActor)) {
				actores.remove(unActor);
			}
		}
	}

	public void removeCasoDeUso(CasoDeUso unCaso) {
		if (unCaso != null) {
			if (casosDeUso.contains(unCaso)) {
				casosDeUso.remove(unCaso);
			}
		}
	}

	public void removeCostoAdicional(CostoAdicional unCosto) {
		if (unCosto != null) {
			if (costosAdicionales.contains(unCosto)) {
				costosAdicionales.remove(unCosto);
			}
		}
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
		actualizado = false;
	}

	public void setEds(String eds) {
		this.eds = eds;
	}

	public void setEsfuerzo(Double esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	/**
	 * método para wicket
	 *
	 * @param fase -> la fase de la estimación
	 */

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	/**
	 * método para wicket
	 *
	 * @param fechaCalculo -> la última fecha de cálculo
	 */

	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

	/**
	 * método para wicket
	 *
	 * @param idEstimacion -> el id de la estimación
	 */

	public void setIdEstimacion(Integer idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * método para wicket
	 *
	 * @param numero -> el número de la estimación
	 */

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	void setUsuario(Usuario usuario) {
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

	public String toString() {
		return "estimación " + (this.idEstimacion == null ? "sin número " : this.idEstimacion.toString());
	}

	private void totalizar() {
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
