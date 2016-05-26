package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cronograma")
@IdClass(TareaCronogramaPK.class)
public class TareaCronograma implements Serializable {

	@Column(name = "dias")
	protected Double dias;
	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Column(name = "horas")
	protected Double horas;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "porcentaje")
	private Double porcentaje;
	@Column(name = "recursos")
	private Integer recursos;
	@Id
	@ManyToOne
	@JoinColumn(name = "idtarea", nullable = false)
	private Tarea tarea;

	public TareaCronograma() {
	}

	public Double getDias() {
		return dias != null ? dias : 0;
	}

	public Integer getDiseñoTecnico() {
		return getTarea() != null ? getTarea().getDiseñoTecnico() : 0;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Double getHoras() {
		return horas != null ? horas : 0;
	}

	public Boolean getIncluir() {
		return incluir;
	}

	public Double getPorcentaje() {
		return porcentaje != null ? porcentaje : 0;
	}

	public Double getPorcentajeTarea() {
		return getTarea() != null ? getTarea().getPorcentaje() : 0;
	}

	public Integer getRecursos() {
		return recursos;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public Integer getTipoCosto() {
		return getTarea() != null ? getTarea().getTipoCosto() : 0;
	}

	public void setDias(Double unosDias) {
		dias = unosDias;
		if (tarea != null) {
			horas = tarea.convertirDiasAHoras(this);
		} else {
			horas = null;
		}
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setHoras(Double unasHoras) {
		horas = unasHoras;
		if (tarea != null) {
			dias = tarea.convertirHorasADias(this);
		} else {
			dias = null;
		}
	}

	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setRecursos(Integer recursos) {
		this.recursos = recursos;
	}

	public void setTarea(Tarea unaTarea) {
		tarea = unaTarea;
		porcentaje = unaTarea.getPorcentaje();
		incluir = unaTarea.isIncluir();
		recursos = 1;
	}

}
