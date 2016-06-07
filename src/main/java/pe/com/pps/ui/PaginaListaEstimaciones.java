package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.model.Estimacion;

import java.util.ArrayList;

public class PaginaListaEstimaciones extends PaginaTabla<Estimacion, ProviderEstimacion, Integer> {

	private static final Logger log = LogManager.getLogger(PaginaListaEstimaciones.class);

	public PaginaListaEstimaciones() {
		super(null);
	}

	public PaginaListaEstimaciones(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void crearColumnas(ArrayList<IColumn<Estimacion, String>> columnas) {
		ClickablePropertyColumn<Estimacion, String> id = new ClickablePropertyColumn<>(Model.of("numero"), "idEstimacion", "idEstimacion");
		columnas.add(id);
		ClickablePropertyColumn<Estimacion, String> nombre = new ClickablePropertyColumn<>(Model.of("nombre"), "nombre", "nombre");
		columnas.add(nombre);
		PropertyColumn<Estimacion, String> eds = new PropertyColumn<Estimacion, String>(Model.of("eds"), "eds") {
			@Override
			public void populateItem(final Item<ICellPopulator<Estimacion>> item, final String componentId, final IModel<Estimacion> rowModel) {
				Label label = new Label(componentId, getDataModel(rowModel));
				item.add(label);
				item.add(new AttributeAppender("class", "eds"));
			}
		};
		columnas.add(eds);
	}

	@Override
	protected ProviderEstimacion crearProvider() {
		return new ProviderEstimacion();
	}

	@Override
	protected String getNombre() {
		return "estimaciones";
	}

	@Override
	protected void irPaginaEdicion() {
		irPaginaEdicion(PaginaEstimacion.class);
	}

	@Override
	public void irPaginaEdicion(Estimacion unaEntidad) {
		log.info("irPaginaEdicion");
		irPaginaEdicion(PaginaEstimacion.class, unaEntidad.getIdEstimacion());
	}

}
