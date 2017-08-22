package pe.com.pps.ui.listaestimaciones;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.componentes.ClickablePropertyColumn;
import pe.com.pps.ui.componentes.PaginaTabla;
import pe.com.pps.ui.estimacion.PaginaEstimacion;
import pe.com.pps.ui.providers.ProviderEstimacion;

import java.util.ArrayList;

@AuthorizeInstantiation("usuario")
public class PaginaListaEstimaciones extends PaginaTabla<Estimacion, ProviderEstimacion, Integer> {


	public PaginaListaEstimaciones() {
		this(new PageParameters());
	}

	public PaginaListaEstimaciones(final PageParameters parameters) {
		super(parameters);
		agregarTitulo();
	}

	private void agregarTitulo() {
		agregarTitulo("lista de estimaciones");
	}

	@Override
	protected void crearColumnas(ArrayList<IColumn<Estimacion, String>> columnas) {
		columnas.add(new ClickablePropertyColumn<>(Model.of("Número"), "numero", "idEstimacion", "numero"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("Fase"), null, "fase", "idEstimacion"));
		columnas.add(new ClickablePropertyColumn<>(Model.of("Nombre"), "nombre", "nombre"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("EDS"), null, "eds", "eds"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("Puntos sin ajustar"), null, "puntosString", "puntos"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("Horas parciales"), null, "esfuerzoString", "esfuerzo"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("Horas totales"), null, "esfuerzoCronograma", "esfuerzo"));
		columnas.add(new PropertyColumnEstilo<>(Model.of("Fecha actualización"), null, "fechaCalculoString", "fecha"));
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
		irPaginaEdicion(PaginaEstimacion.class, unaEntidad.getIdEstimacion());
	}

}
