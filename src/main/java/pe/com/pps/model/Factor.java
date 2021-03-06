package pe.com.pps.model;

import pe.trazos.dao.entidad.Entidad;

import javax.persistence.*;
import java.io.Serializable;

@DiscriminatorColumn(name = "tipofactor", discriminatorType = DiscriminatorType.INTEGER)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "factor")
public abstract class Factor extends Entidad<Integer> implements Serializable {

	@Column(name = "descripcion")
	private String descripcion;
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
	private Double peso;
	@Column(name = "tipofactor", insertable = false, updatable = false)
	private Integer tipoFactor;

	public Factor() {
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getId() {
		return idFactor;
	}


	public Integer getIdFactor() {
		return idFactor;
	}

	Integer getMaximo() {
		return maximo != null ? maximo : 0;
	}

	Integer getMinimo() {
		return minimo != null ? minimo : 0;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPeso() {
		return peso;
	}

	Integer getTipoFactor() {
		return tipoFactor;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public void setPeso(Double peso) {
		this.peso = peso;
	}


	public void setTipoFactor(Integer tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public String toString() {
		return String.format("%s - tipo %s", nombre, tipoFactor);
	}

}