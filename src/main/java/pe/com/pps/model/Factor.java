package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "factor")
public class Factor implements Serializable {

	@OneToMany
	@JoinColumn(name = "idfactor")
	private List<EstimacionFactor> factoresEstimacion;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private float peso;
	@ManyToOne
	@JoinColumn(name = "idTipoFactor")
	private TipoDeFactor tipoDeFactor;
	private int valormaximo;
	private int valorminimo;
	public Factor() {
	}

	public List<EstimacionFactor> getFactoresEstimacion() {
		return factoresEstimacion;
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

	public TipoDeFactor getTipoDeFactor() {
		return this.tipoDeFactor;
	}

	public int getValormaximo() {
		return this.valormaximo;
	}

	public int getValorminimo() {
		return this.valorminimo;
	}

	public void setFactoresEstimacion(List<EstimacionFactor> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
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

	public void setTipoDeFactor(TipoDeFactor tipoDeFactor) {
		this.tipoDeFactor = tipoDeFactor;
	}

	public void setValormaximo(int valormaximo) {
		this.valormaximo = valormaximo;
	}

	public void setValorminimo(int valorminimo) {
		this.valorminimo = valorminimo;
	}

}