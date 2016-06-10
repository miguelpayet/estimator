package pe.com.pps.ui.providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Estimacion;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

	private static final Logger log = LogManager.getLogger(ProviderEstimacion.class);

	public ProviderEstimacion() {
		super(new FiltroTablaNombre());
		setClaseDominio(Estimacion.class);
	}

	@Override
	public IModel model(Object object) {
		return new Model<>((Estimacion) object);
	}

	@Override
	protected void setearFiltros(QueryCriteria query) {
		String valor = getFilterState().getNombre().trim();
		Integer valorEntero = null;
		log.debug("valor {}", valor);
		try {
			valorEntero = Integer.valueOf(valor);
			log.debug("valor entero {}", valorEntero);
		} catch (NumberFormatException e) {
			log.debug("no es número");
		}
		if (valorEntero != null) {
			query.getCriteria().add(Restrictions.eq("idEstimacion", valorEntero));
		} else {
			query.getCriteria().add(Restrictions.or(Restrictions.like("nombre", "%" + valor + "%"), Restrictions.like("eds", "%" + valor + "%")));
		}
	}

}
