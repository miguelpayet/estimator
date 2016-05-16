package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factor_estimacion")
@IdClass(FactorEstimacionPK.class)
public class FactorEstimacion implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion")
	Estimacion estimacion;
	@Id
	@ManyToOne
	@JoinColumn(name = "idfactor")
	Factor factor;
	@Column(name = "valor")
	private Integer valor;

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
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

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
