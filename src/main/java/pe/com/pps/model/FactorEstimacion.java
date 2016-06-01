package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(FactorEstimacionPK.class)
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

	public Double getComplejidad() {
		return getValor() * getFactor().getPeso();
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
	}

	public Integer getMaximo() {
		return getFactor() != null ? getFactor().getMaximo() : 0;
	}

	public Integer getMinimo() {
		return getFactor() != null ? getFactor().getMinimo() : 0;
	}

	public String getNombre() {
		return getFactor() != null ? getFactor().getNombre() : "sin factor";
	}

	public Double getPeso() {
		return getFactor() != null ? getFactor().getPeso() : 0.0;
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
