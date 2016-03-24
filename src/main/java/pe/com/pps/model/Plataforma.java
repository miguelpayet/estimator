package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the plataforma database table.
 * 
 */
@Entity
@NamedQuery(name="Plataforma.findAll", query="SELECT p FROM Plataforma p")
public class Plataforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idplataforma;

	private String nombre;

	//bi-directional many-to-many association to Funcionalidad
	@ManyToMany(mappedBy="plataformas")
	private List<Funcionalidad> funcionalidads;

	//bi-directional many-to-one association to PlataformaValor
	@OneToMany(mappedBy="plataforma")
	private List<PlataformaValor> plataformaValors;

	public Plataforma() {
	}

	public int getIdplataforma() {
		return this.idplataforma;
	}

	public void setIdplataforma(int idplataforma) {
		this.idplataforma = idplataforma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Funcionalidad> getFuncionalidads() {
		return this.funcionalidads;
	}

	public void setFuncionalidads(List<Funcionalidad> funcionalidads) {
		this.funcionalidads = funcionalidads;
	}

	public List<PlataformaValor> getPlataformaValors() {
		return this.plataformaValors;
	}

	public void setPlataformaValors(List<PlataformaValor> plataformaValors) {
		this.plataformaValors = plataformaValors;
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().add(plataformaValor);
		plataformaValor.setPlataforma(this);

		return plataformaValor;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().remove(plataformaValor);
		plataformaValor.setPlataforma(null);

		return plataformaValor;
	}

}