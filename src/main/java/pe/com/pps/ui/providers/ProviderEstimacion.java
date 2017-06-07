package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.model.IModel;
import pe.com.pps.model.Estimacion;
import pe.trazos.dao.query.QueryFiltrado;
import pe.trazos.dao.query.TipoAgrupacionFiltro;
import pe.trazos.dao.query.TipoFiltro;
import pe.trazos.dao.query.FiltroQueryAgrupado;

public class ProviderEstimacion extends ProviderTabla<Estimacion, FiltroTablaNombre> {

	public ProviderEstimacion() {
		super(new FiltroTablaNombre());
		setClaseDominio(Estimacion.class);
		setSort("numero", SortOrder.DESCENDING);
	}

	@Override
	protected void filtrar(QueryFiltrado query) {
		String valor = getFilterState().getNombre().trim();
		try {
			query.agregarFiltro("numero", Integer.valueOf(valor), TipoFiltro.EQUAL);
		} catch (NumberFormatException e) {
			FiltroQueryAgrupado filtro = new FiltroQueryAgrupado(TipoAgrupacionFiltro.OR);
			valor = "%" + valor + "%";
			filtro.agregar("nombre", valor, TipoFiltro.EQUAL);
			filtro.agregar("eds", valor, TipoFiltro.EQUAL);
			query.agregarFiltro(filtro);
		}
	}

	@Override
	public IModel<Estimacion> model(Estimacion object) {
		return null;
	}

	@Override
	public void ordenarQuery(QueryFiltrado unQuery) {
		SortOrder orden = getSortState().getPropertySortOrder("numero");
		unQuery.addOrder("numero", orden);
		unQuery.addOrder("fase", orden);
		unQuery.addOrder("fase", SortOrder.ASCENDING);
	}

}
