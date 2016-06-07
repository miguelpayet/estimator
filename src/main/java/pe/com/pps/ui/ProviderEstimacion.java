package pe.com.pps.ui;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;

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
