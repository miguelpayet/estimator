package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cronograma")
@IdClass(CronogramaPK.class)
public class Cronograma implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	private Estimacion estimacion;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "porcentaje")
	private Float porcentaje;
	@Column(name = "recursos")
	private Integer recursos;
	@Id
	@ManyToOne
	@JoinColumn(name = "idtarea", nullable = false)
	private Tarea tarea;

	public Cronograma() {
	}

}
