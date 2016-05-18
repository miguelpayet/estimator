package pe.com.pps.model;

import javax.persistence.*;

@Entity
@Table(name = "caso_de_uso")
@IdClass(CasoDeUsoPK.class)
public class CasoDeUso extends Puntuable {

	@Id
	@Column(name = "numcaso")
	private Integer numCaso;

	public CasoDeUso() {
		super();
	}

	public Integer getNumCaso() {
		return numCaso;
	}

	public Punto getPunto() {
		return super.getPunto(TipoPunto.CASO_DE_USO);
	}

	public void setNumCaso(Integer numCaso) {
		this.numCaso = numCaso;
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getSimpleName(), descripcion);
		} else {
			return super.toString();
		}
	}

}
