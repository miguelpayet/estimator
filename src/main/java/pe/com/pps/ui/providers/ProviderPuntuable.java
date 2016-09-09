package pe.com.pps.ui.providers;

import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Puntuable;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ProviderPuntuable<T extends Puntuable> extends ProviderGenerico<T> {

	public ProviderPuntuable(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	protected List<T> getData() {
		List<T> l = getDataSet().stream().collect(Collectors.toList());
		Collections.sort(l, new ComparadorPuntuable());
		return l;
	}

	protected abstract Set<T> getDataSet();

}
