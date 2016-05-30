package pe.com.pps.ui;

import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;

import java.util.ArrayList;
import java.util.List;

public class ProviderCasoDeUso extends ProviderGenerico<CasoDeUso> {

	public ProviderCasoDeUso(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void add(CasoDeUso item) {
		estimacion.addCasoDeUso(item);
	}

	@Override
	protected List<CasoDeUso> getData() {
		return new ArrayList<>(estimacion.getCasosDeUso());
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
