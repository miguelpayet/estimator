package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;
import pe.trazos.dao.query.QueryFiltrado;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

	public ProviderEstimacion() {
		super(new FiltroTablaNombre());
		setClaseDominio(Estimacion.class);
		setSort("numero", SortOrder.DESCENDING);
	}

	@Override
	public void filtrar(QueryFiltrado<Estimacion> query) {
		String valor = getFilterState().getNombre().trim();
		try {
			final Integer numero = Integer.valueOf(valor);
			query.setFiltro((qry, root, builder) -> qry.where(builder.equal(root.get("numero"), numero)));
		} catch (NumberFormatException e) {
			final String cadena = "%" + valor + "%";
			query.setFiltro((qry, root, builder) -> qry.where(builder.or(builder.like(root.get("nombre"), cadena), builder.like(root.get("eds"), cadena))));
		}
	}

	@Override
	public IModel<Estimacion> model(Estimacion object) {
		return new Model<>(object);
	}

	@Override
	public void ordenarQuery(QueryFiltrado unQuery) {
		SortOrder orden = getSortState().getPropertySortOrder("numero");
		unQuery.addOrden("numero", orden);
		unQuery.addOrden("fase", orden);
		unQuery.addOrden("fase", SortOrder.ASCENDING);
	}

}
