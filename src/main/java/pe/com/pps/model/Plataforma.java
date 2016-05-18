package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plataforma")
public class Plataforma implements Serializable {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plataforma")
	private List<Actor> actores;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plataforma")
	private List<CasoDeUso> casosDeUso;
	@Column(name = "factorproductividad")
	private Float factorProductividad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idplataforma")
	private Integer idPlataforma;
	@Column(name = "nombre")
	private String nombre;

	public Plataforma() {
	}

}