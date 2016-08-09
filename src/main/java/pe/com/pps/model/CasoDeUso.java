package pe.com.pps.model;

import javax.persistence.*;

@Entity
@Table(name = "caso_de_uso")
public class CasoDeUso extends Puntuable implements Identificable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcaso")
	private Integer idCaso;
	@ManyToOne
	@JoinColumn(name = "idplataforma", nullable = false)
	protected Plataforma plataforma;

	public CasoDeUso() {
		super();
	}

	@Override
	public Double getFactorProductividad() {
		return getPlataforma().getFactorProductividad();
	}

	@Override
	public Integer getId() {
		return idCaso;
	}

	public Plataforma getPlataforma() {
		return plataforma != null ? plataforma : new Plataforma();
	}

	public Punto getPunto() {
		return super.getPunto(TipoPunto.CASO_DE_USO);
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getSimpleName(), descripcion);
		} else {
			return super.toString();
		}
	}

}
