package pe.com.pps.ui.estimacion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.provider.IEditableDataProvider;
import pe.com.pps.model.Actor;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.providers.ProviderActor;

import java.util.List;

/**
 * panel con el grid editable de actores
 */
public class PanelActores extends PanelPuntuable<Actor> {

	class EditableGridActores extends EditableGrid<Actor, String> {

		EditableGridActores(String id, List<? extends IColumn<Actor, String>> iColumns, IEditableDataProvider<Actor, String> dataProvider, long rowsPerPage, Class<Actor> clazz) {
			super(id, iColumns, dataProvider, rowsPerPage, clazz);
		}

		@Override
		protected void onError(AjaxRequestTarget target) {
			target.add(panelFeedback);
		}

	}

	PanelActores(String id, Estimacion unaEstimacion, FeedbackPanel unFeedbackPanel) {
		super(id, unaEstimacion, unFeedbackPanel);
		agregarGridActores();
	}

	private void agregarGridActores() {
		EditableGridActores grid = new EditableGridActores("grid-actores", columnasPuntuable(), provider, FILAS_GRID, Actor.class) {

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(panelFeedback);
			}

		};
		grid.setTableCss("actores");
		add(grid);
	}

	@Override
	protected void init(Estimacion unaEstimacion) {
		provider = new ProviderActor(getEstimacion());
	}

}
