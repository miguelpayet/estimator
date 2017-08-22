package pe.com.pps.ui.estimacion;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.column.AbstractEditablePropertyColumn;
import org.wicketstuff.egrid.column.EditableCellPanel;
import org.wicketstuff.egrid.column.EditableRequiredDropDownCellPanel;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Plataforma;
import pe.com.pps.model.Puntuable;
import pe.com.pps.ui.providers.ProviderCasoDeUso;
import pe.trazos.dao.factory.DaoFactory;

import java.util.List;

/**
 * panel con el grid editable de casos de uso
 */
public class PanelCasosDeUso extends PanelPuntuable<CasoDeUso> {

	public PanelCasosDeUso(String id, Estimacion unaEstimacion, FeedbackPanel unPanelFeedback) {
		super(id, unaEstimacion, unPanelFeedback);
		agregarGridCasosDeUso();
	}

	private void agregarGridCasosDeUso() {
		EditableGrid<CasoDeUso, String> grid = new EditableGrid<>("grid-casos-de-uso", columnasCasosDeUso(), provider, FILAS_GRID, CasoDeUso.class);
		grid.setTableCss("casos-de-uso");
		add(grid);
	}

	private <T extends Puntuable> List<AbstractEditablePropertyColumn<T, String>> columnasCasosDeUso() {
		List<AbstractEditablePropertyColumn<T, String>> columns = columnasPuntuable();
		DaoPlataforma dp = DaoFactory.getInstance().crearDao(Plataforma.class, DaoPlataforma.class);
		List<Plataforma> plataformas = dp.listar();
		columns.add(new AbstractEditablePropertyColumn<T, String>(new Model<>("Plataforma"), "plataforma") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, plataformas);
			}
		});
		return columns;
	}

	@Override
	protected void init(Estimacion unaEstimacion) {
		provider = new ProviderCasoDeUso(getEstimacion());
	}

}
