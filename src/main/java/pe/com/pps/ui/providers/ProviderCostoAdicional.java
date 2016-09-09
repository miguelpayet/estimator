package pe.com.pps.ui.providers;

import pe.com.pps.model.CostoAdicional;
import pe.com.pps.model.Estimacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProviderCostoAdicional extends ProviderGenerico<CostoAdicional> {

	public ProviderCostoAdicional(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void add(CostoAdicional item) {
		estimacion.addCostoAdicional(item);
	}

	@Override
	protected List<CostoAdicional> getData() {
		return new ArrayList<>(estimacion.getCostosAdicionales());
	}

	@Override
	public void remove(CostoAdicional item) {
		estimacion.removeCostoAdicional(item);
	}

	@Override
	public long size() {
		Set<CostoAdicional> costos = estimacion.getCostosAdicionales();
		return costos == null ? 0 : costos.size();
	}
}
