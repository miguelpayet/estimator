package pe.com.pps.ui.listaestimaciones;

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
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.componentes.ClickablePropertyColumn;
import pe.com.pps.ui.componentes.PaginaTabla;
import pe.com.pps.ui.providers.ProviderEstimacion;

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
		columnas.add(new PropertyColumnEstilo<>(Model.of("eds"), null, "eds", "eds"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("puntos sin ajustar"), null, "puntos", "puntos"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("horas sin gestión"), null, "esfuerzo", "esfuerzo"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("fecha actualización"), null, "fechaCalculo", "fecha"));
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
