package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cronograma")
public class Cronograma implements Serializable {

	@EmbeddedId
	private CronogramaPK CronogramaPK;
	@ManyToOne
	@JoinColumn(name = "idestimacion")
	private Estimacion estimacion;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "porcentaje")
	private Float porcentaje;
	@Column(name = "recursos")
	private Integer recursos;
	@ManyToOne
	@JoinColumn(name = "idtarea")
	private Tarea tarea;

	public Cronograma() {}

}
