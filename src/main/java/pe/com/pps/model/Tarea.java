package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

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
	private Float porcentaje;
	@ManyToOne
	@JoinColumn(name = "idproveedor")
	private Proveedor proveedor;
	@Column(name = "tipocosto")
	private Integer tipoCosto;

	public Tarea() {
	}

}