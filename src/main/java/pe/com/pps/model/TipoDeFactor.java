package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_de_factor")
public class TipoDeFactor implements Serializable {

	@OneToMany(mappedBy = "idtipofactor")
	private List<Factor> factores;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoFactor;
	private String nombre;

	public TipoDeFactor() {
	}

	public Factor addFactor(Factor factor) {
		getFactores().add(factor);
		factor.setTipoDeFactor(this);
		return factor;
	}

	public List<Factor> getFactores() {
		return this.factores;
	}

	public int getIdTipoFactor() {
		return this.idTipoFactor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Factor removeFactor(Factor factor) {
		getFactores().remove(factor);
		factor.setTipoDeFactor(null);
		return factor;
	}

	public void setFactores(List<Factor> factores) {
		this.factores = factores;
	}

	public void setIdTipoFactor(int idTipoFactor) {
		this.idTipoFactor = idTipoFactor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}