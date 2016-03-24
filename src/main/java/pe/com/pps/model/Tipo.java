package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo database table.
 * 
 */
@Entity
@NamedQuery(name="Tipo.findAll", query="SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipo;

	private String nombre;

	//bi-directional many-to-one association to Interfaz
	@OneToMany(mappedBy="tipo")
	private List<Interfaz> interfazs;

	//bi-directional many-to-one association to PlataformaValor
	@OneToMany(mappedBy="tipo")
	private List<PlataformaValor> plataformaValors;

	public Tipo() {
	}

	public int getIdtipo() {
		return this.idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Interfaz> getInterfazs() {
		return this.interfazs;
	}

	public void setInterfazs(List<Interfaz> interfazs) {
		this.interfazs = interfazs;
	}

	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfazs().add(interfaz);
		interfaz.setTipo(this);

		return interfaz;
	}

	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfazs().remove(interfaz);
		interfaz.setTipo(null);

		return interfaz;
	}

	public List<PlataformaValor> getPlataformaValors() {
		return this.plataformaValors;
	}

	public void setPlataformaValors(List<PlataformaValor> plataformaValors) {
		this.plataformaValors = plataformaValors;
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().add(plataformaValor);
		plataformaValor.setTipo(this);

		return plataformaValor;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().remove(plataformaValor);
		plataformaValor.setTipo(null);

		return plataformaValor;
	}

}