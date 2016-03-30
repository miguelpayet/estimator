package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "interfaz")
public class Interfaz implements Serializable {

	@ManyToMany(mappedBy = "interfaces")
	private List<Funcionalidad> funcionalidades;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInterfaz;
	@ManyToOne
	@JoinColumn(name = "idtipocambio")
	private TipoDeCambio tipoDeCambio;

	public Interfaz() {
	}

	public int getIdInterfaz() {
		return this.idInterfaz;
	}

	public TipoDeCambio getTipoDeCambio() {
		return this.tipoDeCambio;
	}

	public void setIdInterfaz(int idInterfaz) {
		this.idInterfaz = idInterfaz;
	}

	public void setTipoDeCambio(TipoDeCambio tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}

}