package pe.com.pps.ui.providers;

import pe.com.pps.model.Puntuable;

import java.util.Comparator;

public class ComparadorPuntuable implements Comparator<Puntuable> {

	@Override
	public int compare(Puntuable o1, Puntuable o2) {
		return o1.getDescripcion().compareToIgnoreCase(o2.getDescripcion());
	}

}
