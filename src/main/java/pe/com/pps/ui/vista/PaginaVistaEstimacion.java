package pe.com.pps.ui.vista;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.collections.ArrayListStack;
import pe.com.pps.model.*;
import pe.com.pps.ui.base.PaginaBaseEstimacion;
import pe.com.pps.ui.providers.*;

import java.util.List;

public class PaginaVistaEstimacion extends PaginaBaseEstimacion {

	private Cronograma cronograma;

	public PaginaVistaEstimacion() {
		this(new PageParameters());
	}

	public PaginaVistaEstimacion(PageParameters unosParametros) {
		super(unosParametros);
		cronograma = new Cronograma(getEstimacion());
		agregarTitulo();
		agregarEstimacion();
		agregarActores();
		agregarCasosDeUso();
		agregarFactores();
		agregarCronograma();
		agregarCostoProveedor();
		agregarCostoAdicional();
		agregarCostoTotal();
		agregarIndicadores();
	}

	private void agregarIndicadores() {
		add(new Label("desviacion-minimo", new PropertyModel<Double>(cronograma, "rangoMinimo")));
		add(new Label("desviacion-maximo", new PropertyModel<Double>(cronograma, "rangoMaximo")));
		add(new Label("meses-cronograma", new PropertyModel<Double>(cronograma, "totalMeses")));
	}

	private void agregarActores() {
		List<ICellPopulator<Actor>> columns = columnasPuntuable();
		ProviderActor pa = new ProviderActor(getEstimacion());
		DataGridView<Actor> dgv = new DataGridView<>("repetidor-actores", columns, pa);
		add(dgv);
	}

	private void agregarCasosDeUso() {
		List<ICellPopulator<CasoDeUso>> columns = columnasPuntuable();
		ProviderCasoDeUso pcu = new ProviderCasoDeUso(getEstimacion());
		DataGridView<CasoDeUso> dgv = new DataGridView<>("repetidor-casos-de-uso", columns, pcu);
		add(dgv);
	}

	private void agregarCostoAdicional() {
		List<ICellPopulator<CostoAdicional>> columns = new ArrayListStack<>();
		columns.add(new PropertyPopulator<>("descripcion"));
		columns.add(new PropertyPopulator<>("costo"));
		ProviderCostoAdicional pc = new ProviderCostoAdicional(getEstimacion());
		DataGridView<CostoAdicional> dgv = new DataGridView<>("repetidor-costo-adicional", columns, pc);
		add(dgv);
	}

	private void agregarCostoProveedor() {
		List<ICellPopulator<CostoProveedor>> columns = new ArrayListStack<>();
		columns.add(new PropertyPopulator<>("proveedor"));
		columns.add(new PropertyPopulator<>("moneda"));
		columns.add(new PropertyPopulator<>("costo"));
		ProviderCostoProveedor pc = new ProviderCostoProveedor(cronograma);
		DataGridView<CostoProveedor> dgv = new DataGridView<>("repetidor-costos", columns, pc);
		add(dgv);
	}

	/**
	 * agregar el cronograma a la página
	 */
	private void agregarCronograma() {
		// cronograma
		List<ICellPopulator<TareaCronograma>> columns = columnasCronograma();
		ProviderTareaCronograma pcu = new ProviderTareaCronograma(getEstimacion());
		DataGridView<TareaCronograma> dgv = new DataGridView<>("repetidor-cronograma", columns, pcu);
		add(dgv);
		// fila de totales
		add(new Label("dias-cronograma", new PropertyModel<>(cronograma, "totalDias")));
		add(new Label("horas-cronograma", new PropertyModel<>(cronograma, "totalHoras")));
	}

	private void agregarEstimacion() {
		add(new Label("id-estimacion", getEstimacion().getIdEstimacion()));
		add(new Label("nombre-estimacion", getEstimacion().getNombre()));
		add(new Label("eds-estimacion", getEstimacion().getEds()));
	}

	private void agregarFactores() {
		List<ICellPopulator<FactorEstimacion>> columns = columnasFactor();
		ProviderFactorAmbiental pfa = new ProviderFactorAmbiental(getEstimacion());
		DataGridView<FactorEstimacion> dgva = new DataGridView<>("repetidor-factores-ambientales", columns, pfa);
		add(dgva);
		ProviderFactorTecnico pft = new ProviderFactorTecnico(getEstimacion());
		DataGridView<FactorEstimacion> dgvt = new DataGridView<>("repetidor-factores-tecnicos", columns, pft);
		add(dgvt);
	}

	private List<ICellPopulator<TareaCronograma>> columnasCronograma() {
		List<ICellPopulator<TareaCronograma>> columns = new ArrayListStack<>();
		columns.add(new PropertyPopulator<>("nombre"));
		columns.add(new PropertyPopulator<>("proveedor"));
		columns.add(new PropertyPopulator<>("porcentaje"));
		columns.add(new PropertyPopulator<>("recursos"));
		columns.add(new PropertyPopulator<>("dias"));
		columns.add(new PropertyPopulator<>("horas"));
		return columns;
	}

	private <T> List<ICellPopulator<T>> columnasFactor() {
		List<ICellPopulator<T>> columns = new ArrayListStack<>();
		columns.add(new PropertyPopulator<>("nombre"));
		columns.add(new PropertyPopulator<>("valor"));
		columns.add(new PropertyPopulator<>("peso"));
		columns.add(new PropertyPopulator<>("complejidad"));
		return columns;
	}

	private <T> List<ICellPopulator<T>> columnasPuntuable() {
		List<ICellPopulator<T>> columns = new ArrayListStack<>();
		PropertyPopulator<T> desc = new PropertyPopulator<T>("descripcion") {
			@Override
			public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
				super.populateItem(cellItem, componentId, rowModel);
				cellItem.add(new AttributeAppender("class", "descripcion"));
			}
		};
		columns.add(desc);
		columns.add(new PropertyPopulator<>("complejidadStr"));
		columns.add(new PropertyPopulator<>("plataforma"));
		return columns;
	}
}
