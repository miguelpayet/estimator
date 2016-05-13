package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plataforma")
public class Plataforma implements Serializable {

	@OneToMany
	@JoinColumn(name = "idplataforma")
	private List<Actor> actores;
	@OneToMany
	@JoinColumn(name = "idplataforma")
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