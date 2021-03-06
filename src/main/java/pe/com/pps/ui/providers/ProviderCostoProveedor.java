package pe.com.pps.ui.providers;

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

	private Cronograma cronograma;

	/**
	 * constructor que recibe el cronograma de la estimación cuya tabla de costos es la razón de ser
	 *
	 * @param unCronograma
	 */
	public ProviderCostoProveedor(Cronograma unCronograma) {
		cronograma = unCronograma;
	}

	/**
	 * sirve para añadir un nuevo item a la lista (en este caso no hace nada)
	 *
	 * @param item item a añadir
	 */
	@Override
	public void add(CostoProveedor item) {
		// método vacío
	}

	/**
	 * este método sirve para liberar memoria descartando objetos asociados pero manteniendo identificadores
	 */
	@Override
	public void detach() {
		// método vacío
	}

	@Override
	public ISortState<String> getSortState() {
		return null;
	}

	@Override
	public Iterator<? extends CostoProveedor> iterator(long first, long count) {
		List<CostoProveedor> costos = cronograma.getCostoProveedores();
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
	 *
	 * @param item item a eliminar
	 */
	@Override
	public void remove(CostoProveedor item) {
		// método vacío
	}

	@Override
	public long size() {
		return cronograma.getCostoProveedores().size(); // evitar repetir el calculo en cada ciclo
	}

}
