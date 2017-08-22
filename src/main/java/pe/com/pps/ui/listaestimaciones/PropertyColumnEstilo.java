package pe.com.pps.ui.listaestimaciones;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
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
