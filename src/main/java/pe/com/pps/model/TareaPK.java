package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tarea database table.
 * 
 */
@Deprecated
@Embeddable
public class TareaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idtarea;

	@Column(insertable=false, updatable=false)
	private int idproveedor;

	public TareaPK() {
	}
	public int getIdtarea() {
		return this.idtarea;
	}
	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}
	public int getIdproveedor() {
		return this.idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TareaPK)) {
			return false;
		}
		TareaPK castOther = (TareaPK)other;
		return 
			(this.idtarea == castOther.idtarea)
			&& (this.idproveedor == castOther.idproveedor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtarea;
		hash = hash * prime + this.idproveedor;
		
		return hash;
	}
}