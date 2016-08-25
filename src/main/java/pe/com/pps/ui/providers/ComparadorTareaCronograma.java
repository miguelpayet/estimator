package pe.com.pps.ui.providers;

import pe.com.pps.model.TareaCronograma;
import pe.trazos.login.modelo.Sort;

import java.util.Comparator;

public class ComparadorTareaCronograma implements Comparator<TareaCronograma> {

	@Override
	public int compare(TareaCronograma o1, TareaCronograma o2) {
		if (o1.getOrden() > o2.getOrden()) {
			return Sort.MAYOR;
		} else if (o1.getOrden() < o2.getOrden()) {
			return Sort.MENOR;
		} else {
			return Sort.IGUAL;
		}
	}

}
