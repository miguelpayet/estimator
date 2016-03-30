package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcategoria;
	private String nombre;

	public Categoria() {
	}

	public int getIdcategoria() {
		return this.idcategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}