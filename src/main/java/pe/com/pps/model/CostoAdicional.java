package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * representa los costos adicionales de hw/sw dentro de una estimación que van aparte del desarrollo normal
 */
@Entity
@Table(name = "costo_adicional")
public class CostoAdicional implements Serializable {

	@Column(name = "costo")
	private Double costo;
	@Column(name = "descripcion")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Id
	@Column(name = "idcosto")
	private int id;

	public Double getCosto() {
		return costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}
}
