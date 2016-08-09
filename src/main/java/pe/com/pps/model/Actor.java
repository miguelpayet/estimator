package pe.com.pps.model;

import org.jboss.logging.Param;
import pe.com.pps.dao.DaoParametro;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor extends Puntuable implements Identificable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idactor")
	private Integer idActor;

	public Actor() {
	}

	@Override
	public Integer getId() {
		return null;
	}

	public Integer getIdActor() {
		return idActor;
	}

	@Override
	public Double getFactorProductividad() {
		DaoParametro dp = new DaoParametro();
		Parametro p = dp.getFactorProductividadActor();
		return p.getValorDouble();
	}

	public Punto getPunto() {
		return super.getPunto(TipoPunto.ACTOR);
	}

	public String toString() {
		if (descripcion != null) {
			return String.format("%s - %s", getClass().getSimpleName(), descripcion);
		} else {
			return super.toString();
		}
	}

}