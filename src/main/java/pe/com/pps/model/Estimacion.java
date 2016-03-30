package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "estimacion")
public class Estimacion implements Serializable {

	@OneToMany
	@JoinColumn(name = "idestimacion")
	private List<EstimacionFactor> factoresEstimacion;
	@OneToMany
	@JoinColumn(name = "idestimacion")
	private List<Funcionalidad> funcionalidads;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstimacion;
	private String nombre;

	public Estimacion() {
	}

	public Funcionalidad addFuncionalidad(Funcionalidad funcionalidad) {
		getFuncionalidads().add(funcionalidad);
		funcionalidad.setEstimacion(this);
		return funcionalidad;
	}

	public List<EstimacionFactor> getFactoresEstimacion() {
		return factoresEstimacion;
	}

	public List<Funcionalidad> getFuncionalidads() {
		return this.funcionalidads;
	}

	public int getIdEstimacion() {
		return this.idEstimacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Funcionalidad removeFuncionalidad(Funcionalidad funcionalidad) {
		getFuncionalidads().remove(funcionalidad);
		funcionalidad.setEstimacion(null);
		return funcionalidad;
	}

	public void setFactoresEstimacion(List<EstimacionFactor> factoresEstimacion) {
		this.factoresEstimacion = factoresEstimacion;
	}

	public void setFuncionalidads(List<Funcionalidad> funcionalidads) {
		this.funcionalidads = funcionalidads;
	}

	public void setIdEstimacion(int idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}