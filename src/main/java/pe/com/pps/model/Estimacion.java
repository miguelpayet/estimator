package pe.com.pps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estimacion database table.
 * 
 */
@Entity
@NamedQuery(name="Estimacion.findAll", query="SELECT e FROM Estimacion e")
public class Estimacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idestimacion;

	private String nombre;

	//bi-directional many-to-one association to Funcionalidad
	@OneToMany(mappedBy="estimacion")
	private List<Funcionalidad> funcionalidads;

	public Estimacion() {
	}

	public int getIdestimacion() {
		return this.idestimacion;
	}

	public void setIdestimacion(int idestimacion) {
		this.idestimacion = idestimacion;
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

	public Funcionalidad addFuncionalidad(Funcionalidad funcionalidad) {
		getFuncionalidads().add(funcionalidad);
		funcionalidad.setEstimacion(this);

		return funcionalidad;
	}

	public Funcionalidad removeFuncionalidad(Funcionalidad funcionalidad) {
		getFuncionalidads().remove(funcionalidad);
		funcionalidad.setEstimacion(null);

		return funcionalidad;
	}

}