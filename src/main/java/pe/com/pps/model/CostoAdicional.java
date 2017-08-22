package pe.com.pps.model;

import pe.trazos.dao.entidad.Entidad;

import javax.persistence.*;

/**
 * representa los costos adicionales de hw/sw dentro de una estimación que van aparte del desarrollo normal
 */
@Entity
@Table(name = "costo_adicional")
public class CostoAdicional extends Entidad<Integer> {

	@Column(name = "costo")
	private Double costo;
	@Column(name = "descripcion")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcosto")
	private Integer id;
	@Column(name = "moneda")
	private String moneda;

	public Double getCosto() {
		return costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Integer getId() {
		return id;
	}

	public String getMoneda() {
		return moneda;
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

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


}
