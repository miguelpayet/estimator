package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clase database table.
 * 
 */
@Entity
@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clase c")
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idclase;

	private String nombre;

	//bi-directional many-to-one association to Factor
	@OneToMany(mappedBy="clase")
	private List<Factor> factors;

	public Clase() {
	}

	public int getIdclase() {
		return this.idclase;
	}

	public void setIdclase(int idclase) {
		this.idclase = idclase;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Factor> getFactors() {
		return this.factors;
	}

	public void setFactors(List<Factor> factors) {
		this.factors = factors;
	}

	public Factor addFactor(Factor factor) {
		getFactors().add(factor);
		factor.setClase(this);

		return factor;
	}

	public Factor removeFactor(Factor factor) {
		getFactors().remove(factor);
		factor.setClase(null);

		return factor;
	}

}