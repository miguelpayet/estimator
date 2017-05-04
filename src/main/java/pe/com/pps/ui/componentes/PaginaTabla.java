package pe.com.pps.ui.componentes;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.com.pps.ui.providers.FiltroTablaNombre;
import pe.com.pps.ui.providers.ProviderTabla;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * T -> clase del modelo
 * V -> provider para dicha clase
 * Z -> tipo del id de la clase del modelo
 */
public abstract class PaginaTabla<T, V extends ProviderTabla, Z extends Serializable> extends PaginaBaseEstimador {

	public PaginaTabla(PageParameters parameters) {
		super(parameters);
		contenido();
		feedback();
	}

	@SuppressWarnings("unchecked")
	private void contenido() {
		// datatable
		ArrayList<IColumn<T, String>> columnas = new ArrayList<>();
		crearColumnas(columnas);
		ProviderTabla proveedor = crearProvider();
		DataTable<T, String> table = new DataTable<>("datatable", columnas, proveedor, 20);
		table.setOutputMarkupId(true);
		add(table);
		// filter box
		FilterForm<FiltroTablaNombre> filterForm = new FilterForm<>("filterForm", proveedor);
		filterForm.add(new TextField<>("nombre", PropertyModel.of(proveedor, "filterState.nombre")));
		add(filterForm);
		// conectar el filter form con el datatable
		FilterToolbar filterToolbar = new FilterToolbar(table, filterForm);
		table.addTopToolbar(filterToolbar);
		table.addTopToolbar(new NavigationToolbar(table));
		table.addTopToolbar(new HeadersToolbar<>(table, proveedor));
		filterForm.add(table);
		// botón para crear
		BotonCrear btnCrear = new BotonCrear("botonCrear");
		filterForm.add(btnCrear);
	}

	protected abstract void crearColumnas(ArrayList<IColumn<T, String>> columnas);

	protected abstract V crearProvider();

	protected void feedback() {
		FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
	}

	protected abstract String getNombre();

	protected abstract void irPaginaEdicion();

	public abstract void irPaginaEdicion(T unaEntidad);

	protected void irPaginaEdicion(Class<? extends PaginaBaseEstimador> unaPagina) {
		setResponsePage(unaPagina);
	}

	protected void irPaginaEdicion(Class<? extends PaginaBaseEstimador> unaPagina, Z unId) {
		PageParameters params = new PageParameters();
		params.add("id", unId);
		setResponsePage(unaPagina, params);
	}


}
