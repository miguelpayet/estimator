package pe.com.pps.model;

import pe.com.pps.dao.DaoPunto;
import pe.trazos.dao.entidad.Entidad;
import pe.trazos.dao.factory.DaoFactory;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Puntuable extends Entidad<Integer> implements Serializable {

	@Column(name = "complejidad", nullable = false)
	private Integer complejidad;
	@Transient
	Integer complejidadAnterior;
	@Column(name = "descripcion")
	protected String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	Estimacion estimacion;
	@Transient
	Punto punto;

	public Integer getComplejidad() {
		return complejidad != null ? complejidad : 0;
	}

	public String getComplejidadStr() {
		String complejidadStr = Complejidad.getNombre(complejidad);
		return complejidadStr != null ? complejidadStr : "Sin complejidad";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	/**
	 * método delegado a las subclases para calcular el factor de productividad del objeto puntuable
	 *
	 * @return el factor de productividad
	 */
	public abstract Double getFactorProductividad();

	public abstract Punto getPunto();

	public Punto getPunto(Integer unTipo) {
		if (punto == null || (complejidad != null && !complejidad.equals(complejidadAnterior))) {
			complejidadAnterior = complejidad;
			DaoPunto dp = DaoFactory.getInstance().crearDao(Punto.class, DaoPunto.class);
			punto = dp.get(unTipo, complejidad);
		}
		return punto != null ? punto : new Punto(); // todo: por lo menos pasarle el tipo (esto debe ser complementado con una validación en la interfaz)
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setComplejidadStr(String unaComplejidad) {
		complejidad = Complejidad.getValor(unaComplejidad);
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

}

