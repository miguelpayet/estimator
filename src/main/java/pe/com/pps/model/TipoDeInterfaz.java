package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="tipo_de_interfaz")
public class TipoDeInterfaz implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoInterfaz;
	private String nombre;

	public TipoDeInterfaz() {
	}

	public int getIdTipoInterfaz() {
		return this.idTipoInterfaz;
	}

	public void setIdTipoInterfaz(int idTipoInterfaz) {
		this.idTipoInterfaz = idTipoInterfaz;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}