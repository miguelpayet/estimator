package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the complejidad database table.
 * 
 */
@Entity
@NamedQuery(name="Complejidad.findAll", query="SELECT c FROM Complejidad c")
public class Complejidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcomplejidad;

	private String nombre;

	//bi-directional many-to-one association to PlataformaValor
	@OneToMany(mappedBy="complejidad")
	private List<PlataformaValor> plataformaValors;

	public Complejidad() {
	}

	public int getIdcomplejidad() {
		return this.idcomplejidad;
	}

	public void setIdcomplejidad(int idcomplejidad) {
		this.idcomplejidad = idcomplejidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PlataformaValor> getPlataformaValors() {
		return this.plataformaValors;
	}

	public void setPlataformaValors(List<PlataformaValor> plataformaValors) {
		this.plataformaValors = plataformaValors;
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().add(plataformaValor);
		plataformaValor.setComplejidad(this);

		return plataformaValor;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().remove(plataformaValor);
		plataformaValor.setComplejidad(null);

		return plataformaValor;
	}

}