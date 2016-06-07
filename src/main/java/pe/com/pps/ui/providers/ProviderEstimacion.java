package pe.com.pps.ui.providers;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.providers.QueryCriteria;
import pe.com.pps.ui.componentes.ProviderTabla;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

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
		query.setFiltro("nombre", getFilterState().getNombre());
	}

}
