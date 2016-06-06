package pe.com.pps.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Complejidad {

	public static final int ALTA = 3;
	public static final int BAJA = 1;
	public static final String LLAVE = "Complejidad";
	public static final int MEDIA = 2;
	private static Map<Integer, String> lista;

	static {
		lista = new HashMap<>();
		lista.put(ALTA, "Alta");
		lista.put(BAJA, "Baja");
		lista.put(MEDIA, "Media");
	}

	public static List<Complejidad> getLista() {
		return new ArrayList(lista.values());
	}

	public static String getNombre(Integer unValor) {
		return lista.get(unValor);
	}

	public static int getValor(String unNombre) {
		for (Map.Entry<Integer, String> e : lista.entrySet()) {
			if (e.getValue().equals(unNombre)) {
				return e.getKey();
			}
		}
		return 0;
	}

}