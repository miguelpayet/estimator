package pe.com.pps.ui.listaestimaciones;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class PropertyColumnEstilo<T, S> extends PropertyColumn<T, S> {

	String estilo;

	public PropertyColumnEstilo(IModel<String> displayModel, S sortProperty, String propertyExpression, String unEstilo) {
		super(displayModel, sortProperty, propertyExpression);
		estilo = unEstilo;
	}

	@Override
	public String getCssClass() {
		return estilo;
	}

}
