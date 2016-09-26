package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cronograma")
@IdClass(TareaCronogramaPK.class)
public class TareaCronograma implements Serializable, Comparable<TareaCronograma> {

	@Column(name = "dias")
	protected Double dias;
	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Column(name = "horas")
	protected Double horas;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "porcentaje")
	private Double porcentaje;
	@Column(name = "recursos")
	private Double recursos;
	@Id
	@ManyToOne
	@JoinColumn(name = "idtarea", nullable = false)
	private Tarea tarea;

	public TareaCronograma() {
	}

	@Override
	public int compareTo(TareaCronograma unaTarea) {
		return this.getTarea().getOrden().compareTo(unaTarea.getTarea().getOrden());
	}

	public Double getCosto() {
		return Util.round(getHoras() * getProveedor().getCosto(), 2);
	}

	public Double getDias() {
		return dias != null ? dias : 0;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Double getHoras() {
		return horas != null ? horas : 0;
	}

	public Boolean getIncluir() {
		return incluir;
	}

	public String getNombre() {
		return getTarea() != null ? getTarea().getNombre() : "sin nombre";
	}

	/**
	 * evalua la tarea asociada a la tareacronograma y devuelve el nombre del proveedor
	 *
	 * @return el nombre del proveedor o un indicador si no tiene tarea o si la tarea no tiene proveedor
	 */
	public String getNombreProveedor() {
		if (getTarea() != null) {
			return getTarea().getProveedor() != null ? getTarea().getProveedor().getNombre() : "sin proveedor";
		}
		return "sin tarea";
	}

	/**
	 * devuelve el orden de la tarea dentro del cronograma o 0 si la tarea asociada es nulo
	 *
	 * @return -> dicho orden
	 */
	public Integer getOrden() {
		return tarea != null ? tarea.getOrden() : 0;
	}

	public Double getPorcentaje() {
		return porcentaje != null ? porcentaje : 0;
	}

	public Double getPorcentajeTarea() {
		return getTarea() != null ? getTarea().getPorcentaje() : 0;
	}

	/**
	 * devuelve el proveedor asociado a la tarea
	 *
	 * @return el proveedor o nulo si no hay tarea asociada o si la tarea no tiene proveedor
	 */
	public Proveedor getProveedor() {
		if (getTarea() != null) {
			return getTarea().getProveedor() != null ? getTarea().getProveedor() : null;
		}
		return null;
	}

	public Double getRecursos() {
		return recursos;
	}

	public Integer getRol() {
		return getTarea() != null ? getTarea().getRol() : 0;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public Integer getTipoCosto() {
		return getTarea() != null ? getTarea().getTipoCosto() : 0;
	}

	public void setDias(Double unosDias) {
		dias = unosDias;
		if (tarea != null) {
			horas = tarea.convertirDiasAHoras(this);
		} else {
			horas = null;
		}
		horas = unosDias * 8; // todo: horas del proveedor
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setHoras(Double unasHoras) {
		horas = unasHoras;
		if (tarea != null) {
			dias = tarea.convertirHorasADias(this);
		} else {
			dias = null;
		}
		dias = unasHoras / 8; // todo: horas del proveedor
	}

	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setRecursos(Double recursos) {
		this.recursos = recursos;
	}

	public void setTarea(Tarea unaTarea) {
		tarea = unaTarea;
		porcentaje = unaTarea.getPorcentaje();
		incluir = unaTarea.isIncluir();
		recursos = 1.0;
	}

	public String toString() {
		return getNombre();
	}

}
