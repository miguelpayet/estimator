package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plataforma_valor database table.
 * 
 */
@Embeddable
public class PlataformaValorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idplataformavalor;

	@Column(insertable=false, updatable=false)
	private int idtipo;

	@Column(insertable=false, updatable=false)
	private int idcomplejidad;

	public PlataformaValorPK() {
	}
	public int getIdplataformavalor() {
		return this.idplataformavalor;
	}
	public void setIdplataformavalor(int idplataformavalor) {
		this.idplataformavalor = idplataformavalor;
	}
	public int getIdtipo() {
		return this.idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public int getIdcomplejidad() {
		return this.idcomplejidad;
	}
	public void setIdcomplejidad(int idcomplejidad) {
		this.idcomplejidad = idcomplejidad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlataformaValorPK)) {
			return false;
		}
		PlataformaValorPK castOther = (PlataformaValorPK)other;
		return 
			(this.idplataformavalor == castOther.idplataformavalor)
			&& (this.idtipo == castOther.idtipo)
			&& (this.idcomplejidad == castOther.idcomplejidad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idplataformavalor;
		hash = hash * prime + this.idtipo;
		hash = hash * prime + this.idcomplejidad;
		
		return hash;
	}
}