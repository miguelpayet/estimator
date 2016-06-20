package pe.com.pps.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import java.io.Serializable;

public class ParametroPK implements Serializable {

	@Column(name = "codlval", insertable = false, nullable = false, updatable = false)
	private Integer codigo;
	@Column(name = "tipolval", insertable = false, nullable = false, updatable = false)
	private String tipo;

	public ParametroPK() {
	}

	public ParametroPK(String unTipo, Integer unCodigo) {
		tipo = unTipo;
		codigo = unCodigo;
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (!(unObjeto instanceof ParametroPK)) {
			return false;
		} else if (this == unObjeto) {
			return true;
		} else {
			ParametroPK pk = (ParametroPK) unObjeto;
			return codigo.equals(pk.getCodigo()) && tipo.equals(pk.getTipo());
		}
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(113, 79).append(codigo).append(tipo).toHashCode();
	}

}
