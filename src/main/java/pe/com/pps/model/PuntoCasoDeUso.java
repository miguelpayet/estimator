package pe.com.pps.model;

import javax.persistence.*;

@Entity
@Table(name = "punto_caso_de_uso")
public class PuntoCasoDeUso {

	@Column(name = "complejidad")
	private Integer complejidad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpuntocasodeuso")
	private Integer idPuntoCasoDeUso;
	@Column(name = "puntos")
	private Integer puntos;
	@Column(name = "tipo")
	private Integer tipo;

	public PuntoCasoDeUso() {
	}

}
