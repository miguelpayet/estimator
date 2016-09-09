package pe.com.pps.ui.providers;

import pe.com.pps.model.Actor;
import pe.com.pps.model.Estimacion;

import java.util.Set;

public class ProviderActor extends ProviderPuntuable<Actor> {

	public ProviderActor(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void add(Actor item) {
		estimacion.addActor(item);
	}

	@Override
	protected Set<Actor> getDataSet() {
		return estimacion.getActores();
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
