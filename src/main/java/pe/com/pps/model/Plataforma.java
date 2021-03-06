package pe.com.pps.model;

import pe.trazos.dao.entidad.Entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plataforma")
public class Plataforma extends Entidad<Integer> implements Serializable {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plataforma")
	private List<CasoDeUso> casosDeUso;
	@Column(name = "factorproductividad")
	private Double factorProductividad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idplataforma")
	private Integer idPlataforma;
	@Column(name = "nombre")
	private String nombre;

	public List<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	Double getFactorProductividad() {
		return factorProductividad != null ? factorProductividad : 0;
	}

	@Override
	public Integer getId() {
		return idPlataforma;
	}

	public String getNombre() {
		return nombre;
	}


	public void setCasosDeUso(List<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}


	public void setFactorProductividad(Double factorProductividad) {
		this.factorProductividad = factorProductividad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return nombre != null ? nombre : "Sin nombre";
	}

}