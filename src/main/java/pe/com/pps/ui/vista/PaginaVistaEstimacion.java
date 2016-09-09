package pe.com.pps.ui.vista;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.collections.ArrayListStack;
import pe.com.pps.model.*;
import pe.com.pps.ui.base.PaginaBaseEstimacion;
import pe.com.pps.ui.estimacion.PanelCostos;
import pe.com.pps.ui.estimacion.PanelResumen;
import pe.com.pps.ui.providers.*;

import java.util.List;

public class PaginaVistaEstimacion extends PaginaBaseEstimacion {

	private class PropertyPopulatorClase<T> extends PropertyPopulator<T> {

		private String clase;

		public PropertyPopulatorClase(String property, String unaClase) {
			super(property);
			clase = property + "-" + unaClase;
		}

		@Override
		public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
			super.populateItem(cellItem, componentId, rowModel);
			cellItem.add(new AttributeAppender("class", clase));
		}
	}

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

	private void agregarActores() {
		List<PropertyPopulatorClase<Actor>> columns = columnasActor();
		ProviderActor pa = new ProviderActor(getEstimacion());
		DataGridView<Actor> dgv = new DataGridView<>("repetidor-actores", columns, pa);
		add(dgv);
	}

	private void agregarCasosDeUso() {
		List<PropertyPopulatorClase<CasoDeUso>> columns = columnasPuntuable();
		ProviderCasoDeUso pcu = new ProviderCasoDeUso(getEstimacion());
		DataGridView<CasoDeUso> dgv = new DataGridView<>("repetidor-casos-de-uso", columns, pcu);
		add(dgv);
	}

	private void agregarCostoAdicional() {
		List<PropertyPopulatorClase<CostoAdicional>> columns = crearColumnas(new String[]{"descripcion", "moneda", "costo"}, "adicional");
		ProviderCostoAdicional pc = new ProviderCostoAdicional(getEstimacion());
		DataGridView<CostoAdicional> dgv = new DataGridView<CostoAdicional>("repetidor-costo-adicional", columns, pc);
		add(dgv);
	}

	private void agregarCostoProveedor() {
		PanelCostos pc = new PanelCostos("costo-proveedor", new Model<>(getEstimacion()));
		add(pc);
	}

	/**
	 * agregar el cronograma a la página
	 */
	private void agregarCronograma() {
		// cronograma
		List<PropertyPopulatorClase<TareaCronograma>> columns = columnasCronograma();
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
		List<PropertyPopulatorClase<FactorEstimacion>> columns = columnasFactor();
		ProviderFactorAmbiental pfa = new ProviderFactorAmbiental(getEstimacion());
		DataGridView<FactorEstimacion> dgva = new DataGridView<>("repetidor-factores-ambientales", columns, pfa);
		add(dgva);
		ProviderFactorTecnico pft = new ProviderFactorTecnico(getEstimacion());
		DataGridView<FactorEstimacion> dgvt = new DataGridView<>("repetidor-factores-tecnicos", columns, pft);
		add(dgvt);
	}

	private void agregarIndicadores() {
		add(new PanelResumen("panel-resumen", new Model<Estimacion>(getEstimacion())));
		/*
		add(new Label("desviacion-minimo", new PropertyModel<Double>(cronograma, "rangoMinimo")));
		add(new Label("desviacion-maximo", new PropertyModel<Double>(cronograma, "rangoMaximo")));
		add(new Label("meses-cronograma", new PropertyModel<Double>(cronograma, "totalMeses")));
		*/
	}

	private <T> List<PropertyPopulatorClase<T>> columnasActor() {
		return crearColumnas(new String[]{"descripcion", "complejidadStr"}, "puntuable");
	}

	private List<PropertyPopulatorClase<TareaCronograma>> columnasCronograma() {
		return crearColumnas(new String[]{"nombre", "proveedor", "porcentaje", "recursos", "dias", "horas"}, "cronograma");
	}

	private <T> List<PropertyPopulatorClase<T>> columnasFactor() {
		return crearColumnas(new String[]{"nombre", "valor", "peso", "complejidad"}, "factor");
	}

	private <T> List<PropertyPopulatorClase<T>> columnasPuntuable() {
		return crearColumnas(new String[]{"descripcion", "complejidadStr", "plataforma"}, "puntuable");
	}

	private <T> List<PropertyPopulatorClase<T>> crearColumnas(String[] columnas, String unSufijo) {
		List<PropertyPopulatorClase<T>> lista = new ArrayListStack<>();
		for (String s : columnas) {
			lista.add(new PropertyPopulatorClase<T>(s, unSufijo));
		}
		return lista;
	}

}
