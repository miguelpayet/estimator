package pe.com.pps.ui.estimacion;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.egrid.column.AbstractEditablePropertyColumn;
import org.wicketstuff.egrid.column.EditableCellPanel;
import org.wicketstuff.egrid.column.EditableRequiredDropDownCellPanel;
import org.wicketstuff.egrid.column.RequiredEditableTextFieldColumn;
import pe.com.pps.model.Complejidad;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Puntuable;
import pe.com.pps.ui.providers.ProviderPuntuable;

import java.util.ArrayList;
import java.util.List;

/**
 * panel base para los grid de puntuables (casos de uso y actores)
 */
public abstract class PanelPuntuable<T extends Puntuable> extends PanelBaseEstimacion {

	protected static final int FILAS_GRID = 10;

	protected FeedbackPanel panelFeedback;
	protected ProviderPuntuable<T> provider;

	public PanelPuntuable(String id, Estimacion unaEstimacion, FeedbackPanel unFeedbackPanel) {
		super(id, new Model<>(unaEstimacion));
		panelFeedback = unFeedbackPanel;
	}

	// http://stackoverflow.com/questions/13995755/generic-method-in-non-generic-class
	protected <T extends Puntuable> List<AbstractEditablePropertyColumn<T, String>> columnasPuntuable() {
		List<AbstractEditablePropertyColumn<T, String>> columns = new ArrayList<>();
		RequiredEditableTextFieldColumn<T, String> descripcion = new RequiredEditableTextFieldColumn<T, String>(new Model<>("Descripción"), "descripcion") {
			@Override
			protected void addBehaviors(final FormComponent<T> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<String>("descripcion")));
			}
		};
		columns.add(descripcion);
		AbstractEditablePropertyColumn<T, String> complejidad = new AbstractEditablePropertyColumn<T, String>(new Model<>("Complejidad"), "complejidadStr") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, Complejidad.getLista());
			}
		};
		columns.add(complejidad);
		return columns;
	}

}
