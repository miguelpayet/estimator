package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factor_estimacion")
public class FactorEstimacion implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idestimacion")
	Estimacion estimacion;
	@ManyToOne
	@JoinColumn(name = "idfactor")
	Factor factor;
	@EmbeddedId
	private FactorEstimacionPK id;
	@Column(name = "valor")
	private Integer valor;

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
	}

	public FactorEstimacionPK getId() {
		return id;
	}

	public Integer getValor() {
		return valor;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}

	public void setId(FactorEstimacionPK id) {
		this.id = id;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
