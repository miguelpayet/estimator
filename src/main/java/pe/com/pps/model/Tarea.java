package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	@Column(name = "disenotecnico")
	private Integer diseñoTecnico;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtarea;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "orden")
	private Integer orden;
	@Column(name = "porcentaje")
	private Double porcentaje;
	@ManyToOne
	@JoinColumn(name = "idproveedor")
	private Proveedor proveedor;
	@Column(name = "tipocosto")
	private Integer tipoCosto;

	public Tarea() {
	}

	public Integer getDiseñoTecnico() {
		return diseñoTecnico;
	}

	public Integer getIdtarea() {
		return idtarea;
	}

	public Boolean getIncluir() {
		return incluir;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getOrden() {
		return orden;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Integer getTipoCosto() {
		return tipoCosto;
	}

	public void setDiseñoTecnico(Integer diseñoTecnico) {
		this.diseñoTecnico = diseñoTecnico;
	}

	public void setIdtarea(Integer idtarea) {
		this.idtarea = idtarea;
	}

	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setTipoCosto(Integer tipoCosto) {
		this.tipoCosto = tipoCosto;
	}

}