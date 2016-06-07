package pe.com.pps.ui.providers;

import pe.com.pps.model.Actor;
import pe.com.pps.model.Estimacion;

import java.util.ArrayList;
import java.util.List;

public class ProviderActor extends ProviderGenerico<Actor> {

	public ProviderActor(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void add(Actor item) {
		estimacion.addActor(item);
	}

	@Override
	protected List<Actor> getData() {
		return new ArrayList<>(estimacion.getActores());
	}

	@Override
	public void remove(Actor item) {
		estimacion.removeActor(item);
	}

	@Override
	public long size() {
		return estimacion.getActores().size();
	}

}
