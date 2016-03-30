package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the factor database table.
 * 
 */
@Deprecated
@Embeddable
public class FactorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idfactor;

	@Column(insertable=false, updatable=false)
	private int idclase;

	public FactorPK() {
	}
	public int getIdfactor() {
		return this.idfactor;
	}
	public void setIdfactor(int idfactor) {
		this.idfactor = idfactor;
	}
	public int getIdclase() {
		return this.idclase;
	}
	public void setIdclase(int idclase) {
		this.idclase = idclase;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FactorPK)) {
			return false;
		}
		FactorPK castOther = (FactorPK)other;
		return 
			(this.idfactor == castOther.idfactor)
			&& (this.idclase == castOther.idclase);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idfactor;
		hash = hash * prime + this.idclase;
		
		return hash;
	}
}