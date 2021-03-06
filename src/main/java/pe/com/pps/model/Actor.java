package pe.com.pps.model;

import pe.com.pps.dao.DaoParametro;
import pe.trazos.dao.factory.DaoFactory;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor extends Puntuable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idactor")
	private Integer idActor;

	@Override
	public Double getFactorProductividad() {
		DaoParametro dp = DaoFactory.getInstance().crearDao(Parametro.class, DaoParametro.class);
		Parametro p = dp.getFactorProductividadActor();
		return p.getValorDouble();
	}

	@Override
	public Integer getId() {
		return null;
	}

	public Integer getIdActor() {
		return idActor;
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