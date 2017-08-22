package pe.com.pps.ui.providers;

import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Factorama;

public class ProviderFactorAmbiental extends ProviderFactorEstimacion {

	/**
	 * constructor que recibe la estimación cuyos factores queremos proveer
	 *
	 * @param unaEstimacion -> dicha estimación
	 */
	public ProviderFactorAmbiental(Estimacion unaEstimacion) {
		super(unaEstimacion);
	}

	@Override
	public void initFactores(Factorama f) {
		setFactores(f.getFactoresAmbientales());
	}

}
