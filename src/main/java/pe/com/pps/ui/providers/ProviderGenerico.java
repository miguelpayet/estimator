package pe.com.pps.ui.providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.provider.IEditableDataProvider;
import pe.com.pps.model.Estimacion;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ProviderGenerico<T extends Serializable> implements IEditableDataProvider<T, String> {

	private static final Logger log = LogManager.getLogger(ProviderGenerico.class);
	protected Estimacion estimacion;

	public ProviderGenerico(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
	}

	@Override
	public void detach() {
	}

	protected abstract List<T> getData();

	@Override
	public ISortState<String> getSortState() {
		return null;
	}

	@Override
	public Iterator<T> iterator(long first, long count) {
		log.debug("iterator first: {} count: {}", first, count);
		List<T> list = getData();
		long toIndex = first + count;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		List<T> sublist = list.subList((int) first, (int) toIndex);
		return sublist.listIterator();
	}

	@Override
	public IModel<T> model(T object) {
		return new Model(object);
	}

}
