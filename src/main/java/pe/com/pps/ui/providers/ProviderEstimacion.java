package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pe.com.pps.model.Estimacion;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

	public ProviderEstimacion() {
		super(new FiltroTablaNombre());
		setClaseDominio(Estimacion.class);
		setSort("numero", SortOrder.DESCENDING);
	}

	@Override
	public IModel model(Object object) {
		return new Model<>((Estimacion) object);
	}

	@Override
	public void ordenarQuery(QueryCriteria unQuery) {
		ISortState<String> sort = getSortState();
		SortOrder order = sort.getPropertySortOrder("numero");
		if (order.equals(SortOrder.ASCENDING)) {
			unQuery.getCriteria().addOrder(Order.asc("numero"));
			unQuery.getCriteria().addOrder(Order.asc("fase"));
		} else {
			unQuery.getCriteria().addOrder(Order.desc("numero"));
			unQuery.getCriteria().addOrder(Order.desc("fase"));
		}
		unQuery.getCriteria().addOrder(Order.asc("fase"));
	}

	@Override
	protected void setearFiltros(QueryCriteria query) {
		String valor = getFilterState().getNombre().trim();
		Integer valorEntero;
		try {
			valorEntero = Integer.valueOf(valor);
		} catch (NumberFormatException e) {
			valorEntero = null;
		}
		if (valorEntero != null) {
			query.getCriteria().add(Restrictions.eq("numero", valorEntero));
		} else {
			query.getCriteria().add(Restrictions.or(Restrictions.like("nombre", "%" + valor + "%"), Restrictions.like("eds", "%" + valor + "%")));
		}
	}

}
