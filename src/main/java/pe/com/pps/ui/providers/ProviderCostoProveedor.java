package pe.com.pps.ui.providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.provider.IEditableDataProvider;
import pe.com.pps.model.CostoProveedor;
import pe.com.pps.model.Cronograma;

import java.util.Iterator;
import java.util.List;

/**
 * data provider para la tabla de costos por proveedor que va en panelcronograma
 */
public class ProviderCostoProveedor implements IEditableDataProvider<CostoProveedor, String> {

	private static Logger log = LogManager.getLogger(ProviderCostoProveedor.class);

	private Cronograma cronograma;

	/**
	 * constructor vacío
	 */
	public ProviderCostoProveedor() {
		throw new RuntimeException("sorpresa");
	}

	/**
	 * constructor que recibe el cronograma de la estimación cuya tabla de costos es la razón de ser
	 *
	 * @param unCronograma
	 */
	public ProviderCostoProveedor(Cronograma unCronograma) { // Estimacion unaEstimacion) {
		log.trace("ProviderCostoProveedor");
		cronograma = unCronograma;
	}

	/**
	 * sirve para añadir un nuevo item a la lista (en este caso no hace nada)
	 *
	 * @param item item a añadir
	 */
	@Override
	public void add(CostoProveedor item) {
		log.trace("add");
	}

	/**
	 * este método sirve para liberar memoria descartando objetos asociados pero manteniendo identificadores
	 */
	@Override
	public void detach() {
		log.trace("detach");
	}

	@Override
	public ISortState<String> getSortState() {
		return null;
	}

	@Override
	public Iterator<? extends CostoProveedor> iterator(long first, long count) {
		List<CostoProveedor> costos = cronograma.getCostoProveedores();
		log.trace("iterator");
		long toIndex = first + count;
		if (toIndex > costos.size()) {
			toIndex = costos.size();
		}
		List<CostoProveedor> sublist = costos.subList((int) first, (int) toIndex);
		return sublist.listIterator();
	}

	@Override
	public IModel<CostoProveedor> model(CostoProveedor object) {
		return new Model<>(object);
	}

	/**
	 * sirve para eliminar un item de la lista -- no implementado
	 * @param item item a eliminar
	 */
	@Override
	public void remove(CostoProveedor item) {
		log.trace("remove");
	}

	@Override
	public long size() {
		log.trace("size");
		return cronograma.getCostoProveedores().size(); // evitar repetir el calculo en cada ciclo
	}

}
