package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.INTEGER)
@Entity
@IdClass(PuntoPK.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "punto")
public class Punto implements Serializable {

	@Id
	@Column(name = "complejidad")
	private Integer complejidad;
	@Column(name = "puntos")
	private Integer puntos;
	@Id
	@Column(name = "tipo", insertable = false, updatable = false)
	private Integer tipo;

	public Punto() {
	}

	public Integer getComplejidad() {
		return complejidad;
	}

	public Integer getPuntos() {
		return puntos != null ? puntos : 0;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
