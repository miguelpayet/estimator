package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_de_cambio")
public class TipoDeCambio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoCambio;
	@OneToMany(mappedBy = "idInterfaz")
	private List<Interfaz> interfaces;
	private String nombre;
	@OneToMany(mappedBy = "tipoDeCambio")
	private List<PlataformaValor> plataformaValores;

	public TipoDeCambio() {
	}

	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfaces().add(interfaz);
		interfaz.setTipoDeCambio(this);

		return interfaz;
	}

	public PlataformaValor addPlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValores().add(plataformaValor);
		plataformaValor.setTipoDeCambio(this);
		return plataformaValor;
	}

	public int getIdTipoCambio() {
		return this.idTipoCambio;
	}

	public List<Interfaz> getInterfaces() {
		return this.interfaces;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<PlataformaValor> getPlataformaValores() {
		return this.plataformaValores;
	}

	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfaces().remove(interfaz);
		interfaz.setTipoDeCambio(null);
		return interfaz;
	}

	public PlataformaValor removePlataformaValor(PlataformaValor plataformaValor) {
		getPlataformaValores().remove(plataformaValor);
		plataformaValor.setTipoDeCambio(null);
		return plataformaValor;
	}

	public void setIdTipoCambio(int idTipoCambio) {
		this.idTipoCambio = idTipoCambio;
	}

	public void setInterfaces(List<Interfaz> interfaces) {
		this.interfaces = interfaces;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPlataformaValores(List<PlataformaValor> plataformaValores) {
		this.plataformaValores = plataformaValores;
	}

}