package pe.com.pps.ui;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.provider.IEditableDataProvider;
import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CasoDeUsoProvider implements IEditableDataProvider<CasoDeUso, String> {

	private Estimacion estimacion;

	public CasoDeUsoProvider(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
	}

	@Override
	public void add(CasoDeUso item) {
		estimacion.addCasoDeUso(item);
	}

	@Override
	public void detach() {
	}

	private List<CasoDeUso> getData() {
		return new ArrayList<>(estimacion.getCasosDeUso());
	}

	@Override
	public ISortState<String> getSortState() {
		return null;
	}

	@Override
	public Iterator<CasoDeUso> iterator(long first, long count) {
		List<CasoDeUso> list = getData();
		long toIndex = first + count;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		List<CasoDeUso> sublist  = list.subList((int) first, (int) toIndex);
		Iterator<CasoDeUso> iterator = sublist.listIterator();
		return iterator;
	}

	@Override
	public IModel<CasoDeUso> model(CasoDeUso object) {
		return new Model(object);
	}

	@Override
	public void remove(CasoDeUso item) {
		// todo: implementar forma de quitar un caso de uso de la estimación
	}

	@Override
	public long size() {
		return estimacion.getCasosDeUso().size();
	}

}
