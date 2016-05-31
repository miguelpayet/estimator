package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {

	@Column(name = "costohora")
	private float costo;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproveedor")
	private Integer idProveedor;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
	private List<Tarea> tareas;

	public Proveedor() {
	}

	public float getCosto() {
		return costo;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}