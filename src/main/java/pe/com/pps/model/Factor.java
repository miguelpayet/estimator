package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the factor database table.
 * 
 */
@Entity
@NamedQuery(name="Factor.findAll", query="SELECT f FROM Factor f")
public class Factor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FactorPK id;

	private String nombre;

	private float peso;

	private int valormaximo;

	private int valorminimo;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="idclase")
	private Clase clase;

	public Factor() {
	}

	public FactorPK getId() {
		return this.id;
	}

	public void setId(FactorPK id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPeso() {
		return this.peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getValormaximo() {
		return this.valormaximo;
	}

	public void setValormaximo(int valormaximo) {
		this.valormaximo = valormaximo;
	}

	public int getValorminimo() {
		return this.valorminimo;
	}

	public void setValorminimo(int valorminimo) {
		this.valorminimo = valorminimo;
	}

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}