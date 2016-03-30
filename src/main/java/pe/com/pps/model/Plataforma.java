package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plataforma")
public class Plataforma implements Serializable {

	@ManyToMany(mappedBy = "plataformas")
	private List<Funcionalidad> funcionalidades;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlataforma;
	private String nombre;
	@OneToMany(mappedBy = "plataforma")
	private List<PlataformaValor> plataformaValores;

	public Plataforma() {
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValores().add(plataformaValor);
		plataformaValor.setPlataforma(this);
		return plataformaValor;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return this.funcionalidades;
	}

	public int getIdPlataforma() {
		return this.idPlataforma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<PlataformaValor> getPlataformaValores() {
		return this.plataformaValores;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValores().remove(plataformaValor);
		plataformaValor.setPlataforma(null);
		return plataformaValor;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPlataformaValores(List<PlataformaValor> plataformaValores) {
		this.plataformaValores = plataformaValores;
	}

}