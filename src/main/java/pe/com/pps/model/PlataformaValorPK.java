package pe.com.pps.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Deprecated
@Embeddable
public class PlataformaValorPK implements Serializable {

	@Column(insertable = false, updatable = false)
	private int idComplejidad;
	private int idPlataformaValor;
	@Column(insertable = false, updatable = false)
	private int idTipoCambio;

	public PlataformaValorPK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlataformaValorPK)) {
			return false;
		}
		PlataformaValorPK castOther = (PlataformaValorPK) other;
		return (this.idPlataformaValor == castOther.idPlataformaValor) && (this.idTipoCambio == castOther.idTipoCambio) && (this.idComplejidad == castOther.idComplejidad);
	}

	public int getIdComplejidad() {
		return this.idComplejidad;
	}

	public int getIdPlataformaValor() {
		return this.idPlataformaValor;
	}

	public int getIdTipoCambio() {
		return this.idTipoCambio;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPlataformaValor;
		hash = hash * prime + this.idTipoCambio;
		hash = hash * prime + this.idComplejidad;
		return hash;
	}

	public void setIdComplejidad(int idComplejidad) {
		this.idComplejidad = idComplejidad;
	}

	public void setIdPlataformaValor(int idPlataformaValor) {
		this.idPlataformaValor = idPlataformaValor;
	}

	public void setIdTipoCambio(int idTipoCambio) {
		this.idTipoCambio = idTipoCambio;
	}

}