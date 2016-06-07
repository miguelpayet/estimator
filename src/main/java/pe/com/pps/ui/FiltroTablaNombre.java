package pe.com.pps.ui;

public class FiltroTablaNombre extends FiltroTabla {

	private String nombre = "";

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean tieneFiltro() {
		boolean tieneFiltro = false;
		if (nombre != null) {
			tieneFiltro = !nombre.equals("");
		}
		return tieneFiltro;
	}

}