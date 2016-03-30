package pe.com.pps.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "estimacion_factor")
public class EstimacionFactor {

	@ManyToOne
	@JoinColumn(name = "idestimacion")
	private Estimacion estimacion;
	@ManyToOne
	@JoinColumn(name = "idfactor")
	private Factor factor;
	@NaturalId
	private int idEstimacion;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstimacionFactor;
	@NaturalId
	private int idFactor;
	private int valor;
	public EstimacionFactor() {
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
	}

	public int getIdEstimacion() {
		return idEstimacion;
	}

	public int getIdEstimacionFactor() {
		return idEstimacionFactor;
	}

	public int getIdFactor() {
		return idFactor;
	}

	public int getValor() {
		return valor;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}

	public void setIdEstimacion(int idEstimacion) {
		this.idEstimacion = idEstimacion;
	}

	public void setIdEstimacionFactor(int idEstimacionFactor) {
		this.idEstimacionFactor = idEstimacionFactor;
	}

	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
