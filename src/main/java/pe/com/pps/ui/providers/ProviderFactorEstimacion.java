package pe.com.pps.ui.providers;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.FactorEstimacion;
import pe.com.pps.model.Factorama;

import java.util.Iterator;
import java.util.List;

public abstract class ProviderFactorEstimacion extends SortableDataProvider<FactorEstimacion, String> {

	private static final long serialVersionUID = 1L;
	private List<FactorEstimacion> factores;

	public ProviderFactorEstimacion(Estimacion unaEstimacion) {
		Factorama f = new Factorama(unaEstimacion);
		initFactores(f);
	}

	public List<FactorEstimacion> getFactores() {
		return factores;
	}

	/**
	 * inicializar la lista de factores de este data provider
	 *
	 * @param f -> el factorama de la estimación con todos los factores
	 */
	public abstract void initFactores(Factorama f);

	@Override
	public Iterator<FactorEstimacion> iterator(long first, long count) {
		return factores.iterator();
	}

	@Override
	public IModel<FactorEstimacion> model(FactorEstimacion object) {
		return new Model<>(object);
	}

	public void setFactores(List<FactorEstimacion> factores) {
		this.factores = factores;
	}

	@Override
	public long size() {
		return factores.size();
	}

}
