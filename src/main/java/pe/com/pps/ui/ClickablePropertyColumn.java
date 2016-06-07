package pe.com.pps.ui;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class ClickablePropertyColumn<T, V> extends AbstractColumn<T, V> {

	private class LinkPanel extends Panel {
		LinkPanel(String id, IModel<T> rowModel, IModel<?> labelModel) {
			super(id);
			Link<T> link = new Link<T>("link", rowModel) {
				@Override
				@SuppressWarnings("unchecked")
				public void onClick() {
					T entidad = getModel().getObject();
					PaginaTabla pt = (PaginaTabla) getPage();
					pt.irPaginaEdicion(entidad);
				}
			};
			add(link);
			link.add(new Label("label", labelModel));
		}
	}
	private String cssClass = null;
	private String property; // la propiedad que se pasará a la celda para que la obtenga del objeto de cada fila

	protected ClickablePropertyColumn(IModel<String> displayModel, String unaPropiedad) {
		super(displayModel, null);
		property = unaPropiedad;
	}

	public ClickablePropertyColumn(IModel<String> displayModel, String unaPropiedad, String unaClaseCss) {
		super(displayModel, null);
		property = unaPropiedad;
		cssClass = unaClaseCss;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
		cellItem.add(new LinkPanel(componentId, rowModel, new PropertyModel<>(rowModel, property)));
	}

}
