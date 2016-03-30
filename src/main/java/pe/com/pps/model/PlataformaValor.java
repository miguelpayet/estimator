package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plataforma_valor")
public class PlataformaValor implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idcomplejidad")
	private Complejidad complejidad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "idplataforma")
	private Plataforma plataforma;
	private int puntajeFuncional;
	@ManyToOne
	@JoinColumn(name = "idtipocambio")
	private TipoDeCambio tipoDeCambio;

	public PlataformaValor() {
	}

	public Complejidad getComplejidad() {
		return this.complejidad;
	}

	public int getId() {
		return this.id;
	}

	public Plataforma getPlataforma() {
		return this.plataforma;
	}

	public int getPuntajeFuncional() {
		return this.puntajeFuncional;
	}

	public TipoDeCambio getTipoDeCambio() {
		return this.tipoDeCambio;
	}

	public void setComplejidad(Complejidad complejidad) {
		this.complejidad = complejidad;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public void setPuntajeFuncional(int puntajeFuncional) {
		this.puntajeFuncional = puntajeFuncional;
	}

	public void setTipoDeCambio(TipoDeCambio tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}

}