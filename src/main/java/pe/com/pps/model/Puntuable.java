package pe.com.pps.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.pps.dao.DaoPunto;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class Puntuable implements Serializable {

	private final static Logger log = LogManager.getLogger(Actor.class);

	@Column(name = "complejidad")
	private Integer complejidad;
	@Transient
	Integer complejidadAnterior;
	@Column(name = "descripcion")
	protected String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	Estimacion estimacion;
	@ManyToOne
	@JoinColumn(name = "idplataforma", nullable = false)
	protected Plataforma plataforma;
	@Transient
	Punto punto;

	public Integer getComplejidad() {
		return complejidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public Punto getPunto(Integer unTipo) {
		if (punto == null || (complejidad != null && !complejidad.equals(complejidadAnterior))) {
			complejidadAnterior = complejidad;
			DaoPunto dp = new DaoPunto();
			punto = dp.get(unTipo, complejidad);
		}
		return punto;
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

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
}
