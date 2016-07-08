package pe.com.pps.ui.providers;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.TareaCronograma;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProviderTareaCronograma implements IDataProvider<TareaCronograma> {

	List<TareaCronograma> tareas;

	public ProviderTareaCronograma(Estimacion unaEstimacion) {
		tareas = unaEstimacion.getTareasCronograma().stream().sorted(new ComparadorTareaCronograma()).filter(TareaCronograma::getIncluir).collect(Collectors.toList());
	}

	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends TareaCronograma> iterator(long first, long count) {
		return tareas.iterator();
	}

	@Override
	public IModel<TareaCronograma> model(TareaCronograma object) {
		return new Model<>(object);
	}

	@Override
	public long size() {
		return tareas.size();
	}

}
