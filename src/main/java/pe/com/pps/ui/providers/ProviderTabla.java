package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import pe.trazos.dao.Dao;
import pe.trazos.dao.entidad.Entidad;
import pe.trazos.dao.factory.DaoFactory;
import pe.trazos.dao.query.QueryFiltrado;

import java.util.Iterator;
import java.util.List;

/*
T -> clase del modelo
V -> clase de filtro
 */
public abstract class ProviderTabla<T extends Entidad<?>, V extends FiltroTabla> extends SortableDataProvider<T, Object> implements IFilterStateLocator<V> {

	private Class<T> claseDominio;
	private V filterState;

	public ProviderTabla() {
	}

	public ProviderTabla(V unFilterState) {
		filterState = unFilterState;
	}

	public abstract void filtrar(QueryFiltrado<T> unQuery);

	public V getFilterState() {
		return filterState;
	}

	public Iterator<T> iterator(long first, long count) {
		QueryFiltrado<T> query = newQuery();
		query.setRango(first, count);
		if (tieneFiltro()) {
			filtrar(query);
		}
		ordenarQuery(query);
		List<T> data = query.listar();
		return data.iterator();
	}

	private QueryFiltrado<T> newQuery() {
		Dao dao = DaoFactory.getInstance().crearDao(claseDominio);
		return new QueryFiltrado<T>(dao );
	}

	public abstract void ordenarQuery(QueryFiltrado unQuery);

	public void setClaseDominio(Class<T> unaClase) {
		claseDominio = unaClase;
	}

	public void setFilterState(V state) {
		filterState = state;
	}

	public long size() {
		QueryFiltrado query = newQuery();
		return query.contarFilas();
	}

	private boolean tieneFiltro() {
		return filterState.tieneFiltro();
	}

}
