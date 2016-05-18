package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PuntoPK implements Serializable {

	@Column(name = "complejidad", insertable = false, nullable = false, updatable = false)
	private Integer complejidad;
	@Column(name = "tipo", insertable = false, nullable = false, updatable = false)
	private Integer tipo;

	public PuntoPK() {

	}

	public PuntoPK(Integer unTipo, Integer unaComplejidad) {
		tipo = unTipo;
		complejidad = unaComplejidad;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof PuntoPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			PuntoPK pk = (PuntoPK) unObjeto;
			return complejidad.equals(pk.getComplejidad()) && tipo.equals(pk.getTipo());
		}
	}

	public Integer getComplejidad() {
		return complejidad;
	}

	public Integer getTipo() {
		return tipo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(97, 51).append(complejidad).append(tipo).toHashCode();
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
