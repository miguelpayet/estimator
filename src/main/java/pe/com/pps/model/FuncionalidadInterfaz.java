package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the funcionalidad_interfaz database table.
 * 
 */
@Entity
@Table(name="funcionalidad_interfaz")
@NamedQuery(name="FuncionalidadInterfaz.findAll", query="SELECT f FROM FuncionalidadInterfaz f")
public class FuncionalidadInterfaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FuncionalidadInterfazPK id;

	public FuncionalidadInterfaz() {
	}

	public FuncionalidadInterfazPK getId() {
		return this.id;
	}

	public void setId(FuncionalidadInterfazPK id) {
		this.id = id;
	}

}