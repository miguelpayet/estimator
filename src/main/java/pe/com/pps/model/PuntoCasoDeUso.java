package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "punto_funcional")
public class PuntoCasoDeUso implements Serializable {

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

	public Integer getComplejidad() {
		return complejidad;
	}

	public Integer getIdPuntoCasoDeUso() {
		return idPuntoCasoDeUso;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setIdPuntoCasoDeUso(Integer idPuntoCasoDeUso) {
		this.idPuntoCasoDeUso = idPuntoCasoDeUso;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
