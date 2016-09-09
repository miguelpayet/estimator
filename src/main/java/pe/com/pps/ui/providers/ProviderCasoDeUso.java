package pe.com.pps.ui.providers;

import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;

import java.util.Set;

public class ProviderCasoDeUso extends ProviderPuntuable<CasoDeUso> {

	public ProviderCasoDeUso(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void add(CasoDeUso item) {
		estimacion.addCasoDeUso(item);
	}

	@Override
	protected Set<CasoDeUso> getDataSet() {
		return estimacion.getCasosDeUso();
	}

	@Override
	public void remove(CasoDeUso item) {
		estimacion.removeCasoDeUso(item);
	}

	@Override
	public long size() {
		return estimacion.getCasosDeUso().size();
	}

}
