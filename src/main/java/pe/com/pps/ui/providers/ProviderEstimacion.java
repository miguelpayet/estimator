package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;
import pe.trazos.dao.query.QueryFiltrado;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

	private static final String CAMPO_EDS = "eds";
	private static final String CAMPO_FASE = "fase";
	private static final String CAMPO_NOMBRE = "nombre";
	private static final String CAMPO_NUMERO = "numero";

	public ProviderEstimacion() {
		super(new FiltroTablaNombre());
		setClaseDominio(Estimacion.class);
		setSort(CAMPO_NUMERO, SortOrder.DESCENDING);
	}

	@Override
	public void filtrar(QueryFiltrado<Estimacion> query) {
		String valor = getFilterState().getNombre().trim();
		try {
			final Integer numero = Integer.valueOf(valor);
			query.setFiltro((qry, root, builder) -> qry.where(builder.equal(root.get(CAMPO_NUMERO), numero)));
		} catch (NumberFormatException e) {
			final String cadena = "%" + valor + "%";
			query.setFiltro((qry, root, builder) -> qry.where(builder.or(builder.like(root.get(CAMPO_NOMBRE), cadena), builder.like(root.get(CAMPO_EDS), cadena))));
		}
	}

	@Override
	public IModel<Estimacion> model(Estimacion object) {
		return new Model<>(object);
	}

	@Override
	public void ordenarQuery(QueryFiltrado unQuery) {
		SortOrder orden = getSortState().getPropertySortOrder(CAMPO_NUMERO);
		unQuery.addOrden(CAMPO_NUMERO, orden);
		unQuery.addOrden(CAMPO_FASE, orden);
		unQuery.addOrden(CAMPO_FASE, SortOrder.ASCENDING);
	}

}
