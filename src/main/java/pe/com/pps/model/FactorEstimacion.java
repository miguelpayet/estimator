package pe.com.pps.model;

import pe.trazos.dao.entidad.Entidad;

import javax.persistence.*;

@Entity
@Table(name = "factor_estimacion")
public class FactorEstimacion extends Entidad<Integer> implements Comparable<FactorEstimacion> {

	@ManyToOne
	@JoinColumn(name = "idestimacion", nullable = false)
	protected Estimacion estimacion;
	@ManyToOne
	@JoinColumn(name = "idfactor", nullable = false)
	protected Factor factor;
	@Id
	@Column(name = "idfactorestimacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFactorEstimacion;
	@Column(name = "valor")
	protected Integer valor;

	public int compareTo(FactorEstimacion unFactor) {
		return getNombre().compareTo(unFactor.getNombre());
	}

	public Double getComplejidad() {
		return getValor() * getFactor().getPeso();
	}

	public String getDescripcion() {
		return getFactor() != null ? getFactor().getDescripcion() : "sin descripción";
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public Factor getFactor() {
		return factor;
	}

	@Override
	public Integer getId() {
		return idFactorEstimacion;
	}

	public Integer getMaximo() {
		return getFactor() != null ? getFactor().getMaximo() : 0;
	}

	public Integer getMinimo() {
		return getFactor() != null ? getFactor().getMinimo() : 0;
	}

	/**
	 * método para wicket - shortcut al nombre del factor
	 *
	 * @return nombre del factor
	 */
	public String getNombre() {
		return getFactor() != null ? getFactor().getNombre() : "sin factor";
	}

	/**
	 * acceso al peso seleccionado para el factor
	 *
	 * @return -> peso del factor en la estimación
	 */
	public Double getPeso() {
		return getFactor() != null ? getFactor().getPeso() : 0.0;
	}

	public Integer getTipo() {
		return getFactor() != null ? getFactor().getTipoFactor() : 0;
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
