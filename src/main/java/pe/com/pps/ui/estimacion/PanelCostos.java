package pe.com.pps.ui.estimacion;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.CostoProveedor;
import pe.com.pps.model.Estimacion;
import pe.com.pps.ui.providers.ProviderCostoProveedor;

/**
 * panel con resumen de costos por proveedor
 */
public class PanelCostos extends PanelBaseCronograma {

	public PanelCostos(String id, IModel<Estimacion> unModelo) {
		super(id, unModelo);
		agregarCostoProveedores();
		agregarTotales();
		agregarDesviacion();
	}

	private void agregarCostoProveedores() {
		ProviderCostoProveedor proveedor = new ProviderCostoProveedor(cronograma);
		DataView<CostoProveedor> dv = new DataView<CostoProveedor>("dataview-costos", proveedor) {
			@Override
			protected void populateItem(Item<CostoProveedor> item) {
				CostoProveedor costo = item.getModelObject();
				RepeatingView rv = new RepeatingView("fila-costos");
				rv.add(new Label(rv.newChildId(), costo.getProveedor().getNombre()));
				rv.add(new Label(rv.newChildId(), costo.getDescripcionHoras()));
				rv.add(new Label(rv.newChildId(), CostoProveedor.MONEDA));
				rv.add(new Label(rv.newChildId(), costo.getDescripcionCosto()));
				item.add(rv);
				item.add(new AttributeModifier("class", "costo-proveedor"));
			}
		};
		add(dv);
	}

	private void agregarTotales() {
		add(new Label("total-horas", new PropertyModel<Double>(cronograma, "totalHoras")));
		add(new Label("total-monto", new PropertyModel<Double>(cronograma, "totalCosto")));
	}

}
