package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

//@DiscriminatorColumn(name = "tipofactor", discriminatorType = DiscriminatorType.INTEGER)
@Entity
@IdClass(FactorEstimacionPK.class)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "factor_estimacion")
public class FactorEstimacion implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	protected Estimacion estimacion;
	@Id
	@ManyToOne
	@JoinColumn(name = "idfactor", nullable = false)
	protected Factor factor;
	@Column(name = "valor")
	protected Integer valor;

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
