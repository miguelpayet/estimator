package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caso_de_uso")
public class CasoDeUso extends Puntuable implements Identificable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcaso")
	private Integer idCaso;

	public CasoDeUso() {
		super();
	}

	@Override
	public Integer getId() {
		return idCaso;
	}

	public Punto getPunto() {
		return super.getPunto(TipoPunto.CASO_DE_USO);
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getSimpleName(), descripcion);
		} else {
			return super.toString();
		}
	}

}
