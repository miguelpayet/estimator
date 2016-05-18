package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@DiscriminatorColumn(name = "tipofactor", discriminatorType = DiscriminatorType.INTEGER)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "factor")
public abstract class Factor implements Serializable {

	@Column(name = "descripcion")
	private String descripcion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="factor")
	Set<FactorEstimacion> factoresEstimacion;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfactor")
	private Integer idFactor;
	@Column(name = "maximo")
	private Integer maximo;
	@Column(name = "minimo")
	private Integer minimo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "peso")
	private Float peso;
	@Column(name = "tipofactor", insertable = false, updatable = false)
	private Integer tipoFactor;

	public Factor() {
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Set<FactorEstimacion> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	public Integer getIdFactor() {
		return idFactor;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public String getNombre() {
		return nombre;
	}

	public Float getPeso() {
		return peso;
	}

	public Integer getTipoFactor() {
		return tipoFactor;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFactoresEstimacion(Set<FactorEstimacion> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
	}

	public void setIdFactor(Integer idFactor) {
		this.idFactor = idFactor;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public void setTipoFactor(Integer tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public String toString() {
		return String.format("%s - tipo %s", nombre, tipoFactor);
	}

}