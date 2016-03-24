package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the plataforma_valor database table.
 * 
 */
@Entity
@Table(name="plataforma_valor")
@NamedQuery(name="PlataformaValor.findAll", query="SELECT p FROM PlataformaValor p")
public class PlataformaValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlataformaValorPK id;

	private int puntajefuncional;

	//bi-directional many-to-one association to Complejidad
	@ManyToOne
	@JoinColumn(name="idcomplejidad")
	private Complejidad complejidad;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="idtipo")
	private Tipo tipo;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne
	@JoinColumn(name="idplataforma")
	private Plataforma plataforma;

	public PlataformaValor() {
	}

	public PlataformaValorPK getId() {
		return this.id;
	}

	public void setId(PlataformaValorPK id) {
		this.id = id;
	}

	public int getPuntajefuncional() {
		return this.puntajefuncional;
	}

	public void setPuntajefuncional(int puntajefuncional) {
		this.puntajefuncional = puntajefuncional;
	}

	public Complejidad getComplejidad() {
		return this.complejidad;
	}

	public void setComplejidad(Complejidad complejidad) {
		this.complejidad = complejidad;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Plataforma getPlataforma() {
		return this.plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

}