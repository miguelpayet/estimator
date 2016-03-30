package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private float peso;
	@ManyToOne
	@JoinColumn(name = "idproveedor")
	private Proveedor proveedor;

	public Tarea() {
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public float getPeso() {
		return this.peso;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}