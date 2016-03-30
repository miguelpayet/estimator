package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "complejidad")
public class Complejidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComplejidad;
	private String nombre;
	//bi-directional many-to-one association to PlataformaValor
	@OneToMany(mappedBy = "complejidad")
	private List<PlataformaValor> plataformaValors;

	public Complejidad() {
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().add(plataformaValor);
		plataformaValor.setComplejidad(this);
		return plataformaValor;
	}

	public int getIdComplejidad() {
		return this.idComplejidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<PlataformaValor> getPlataformaValors() {
		return this.plataformaValors;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValors().remove(plataformaValor);
		plataformaValor.setComplejidad(null);
		return plataformaValor;
	}

	public void setIdComplejidad(int idComplejidad) {
		this.idComplejidad = idComplejidad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPlataformaValors(List<PlataformaValor> plataformaValors) {
		this.plataformaValors = plataformaValors;
	}

}