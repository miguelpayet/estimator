package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;

import java.util.Iterator;
import java.util.List;

/*
T -> clase del modelo
V -> clase de filtro
 */
public abstract class ProviderTabla<T, V extends FiltroTabla> extends SortableDataProvider implements IFilterStateLocator<V> {

	private Class<T> claseDominio;
	private V filterState;

	public ProviderTabla() {
	}

	public ProviderTabla(V unFilterState) {
		filterState = unFilterState;
	}

	public V getFilterState() {
		return filterState;
	}

	public Iterator<T> iterator(long first, long count) {
		QueryCriteria query = newQuery();
		query.setRango(first, count);
		if (tieneFiltro()) {
			setearFiltros(query);
		}
		ordenarQuery(query);
		List<T> data = query.leer();
		return data.iterator();
	}

	@Deprecated
	QueryCriteria newQuery(Class unaClase) {
		return new QueryCriteria(unaClase);
	}

	private QueryCriteria newQuery() {
		return new QueryCriteria(claseDominio);
	}

	public abstract void ordenarQuery(QueryCriteria unQuery);

	public void setClaseDominio(Class<T> unaClase) {
		claseDominio = unaClase;
	}

	public void setFilterState(V state) {
		filterState = state;
	}

	protected abstract void setearFiltros(QueryCriteria query);

	public long size() {
		QueryCriteria query = newQuery();
		return query.contarFilas();
	}

	private boolean tieneFiltro() {
		return filterState.tieneFiltro();
	}

}
