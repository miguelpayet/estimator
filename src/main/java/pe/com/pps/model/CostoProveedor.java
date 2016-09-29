package pe.com.pps.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * clase que contiene el costo total de un proveedor dentro de una estimación
 * sirve para poner un datatable de proveedores y su costo en la página de estimación
 */
public class CostoProveedor implements Serializable {

	private Double costo;
	private Double horas;
	private Proveedor proveedor;

	public CostoProveedor(Proveedor unProveedor) {
		proveedor = unProveedor;
		costo = 0d;
		horas = 0d;
	}

	public Double getCosto() {
		return Util.round(costo, 2);
	}

	public String getDescripcionCosto() {
		return Util.format(getCosto());
	}

	public String getDescripcionHoras() {
		return Util.format(getHoras());
	}

	public Double getHoras() {
		return Util.round(horas, 2);
	}

	public String getMoneda() {
		return "PEN";
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void sumar(Double unCosto, Double unasHoras) {
		costo += unCosto;
		horas += unasHoras;
	}

}
