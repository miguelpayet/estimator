package pe.com.pps.ui.vista;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.collections.ArrayListStack;
import pe.com.pps.model.*;
import pe.com.pps.ui.base.PaginaBaseEstimacion;
import pe.com.pps.ui.providers.*;

import java.util.List;

public class PaginaVistaEstimacion extends PaginaBaseEstimacion {

	public PaginaVistaEstimacion() {
		this(new PageParameters());
	}

	public PaginaVistaEstimacion(PageParameters unosParametros) {
		super(unosParametros);
		agregarTitulo();
		agregarEstimacion();
		agregarActores();
		agregarCasosDeUso();
		agregarFactores();
		agregarCronograma();
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
		columns.add(new PropertyPopulator<>("descripcion"));
		columns.add(new PropertyPopulator<>("complejidadStr"));
		columns.add(new PropertyPopulator<>("plataforma"));
		return columns;
	}

	private void agregarCronograma() {
		List<ICellPopulator<TareaCronograma>> columns = columnasCronograma();
		ProviderTareaCronograma pcu = new ProviderTareaCronograma(getEstimacion());
		DataGridView<TareaCronograma> dgv = new DataGridView<>("repetidor-cronograma", columns, pcu);
		add(dgv);
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
}
