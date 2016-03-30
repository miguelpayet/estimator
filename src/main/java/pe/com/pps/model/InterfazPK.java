package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the interfaz database table.
 * 
 */
@Deprecated
@Embeddable
public class InterfazPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idinterfaz;

	@Column(insertable=false, updatable=false)
	private int idtipo;

	public InterfazPK() {
	}
	public int getIdinterfaz() {
		return this.idinterfaz;
	}
	public void setIdinterfaz(int idinterfaz) {
		this.idinterfaz = idinterfaz;
	}
	public int getIdtipo() {
		return this.idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InterfazPK)) {
			return false;
		}
		InterfazPK castOther = (InterfazPK)other;
		return 
			(this.idinterfaz == castOther.idinterfaz)
			&& (this.idtipo == castOther.idtipo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idinterfaz;
		hash = hash * prime + this.idtipo;
		
		return hash;
	}
}