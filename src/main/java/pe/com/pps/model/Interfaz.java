package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the interfaz database table.
 * 
 */
@Entity
@NamedQuery(name="Interfaz.findAll", query="SELECT i FROM Interfaz i")
public class Interfaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InterfazPK id;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="idtipo")
	private Tipo tipo;

	public Interfaz() {
	}

	public InterfazPK getId() {
		return this.id;
	}

	public void setId(InterfazPK id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}