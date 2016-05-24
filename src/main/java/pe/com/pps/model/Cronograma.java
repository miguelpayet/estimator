package pe.com.pps.model;

import com.google.common.collect.HashMultimap;

import java.util.Set;

public class Cronograma {

	private Estimacion estimacion;
	private HashMultimap<Integer, TareaCronograma> mapaTareas;

	public Cronograma(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
		init();
	}

	public void calcular() throws ExcepcionCronograma {
		double a = getTareaFija().getHoras();
		double pctDuracion = getTareaDuracion().getPorcentaje();
		double pctDiseñoTecnico = getTareaDiseñoTecnico().getPorcentaje();
		double b = (pctDuracion * (pctDiseñoTecnico * (estimacion.getEsfuerzo() - a))) / (1 + (pctDuracion * pctDiseñoTecnico));
		getTareaDuracion().setHoras(b);
		for (TareaCronograma t : getTareasEsfuerzo()) {
			t.setHoras((estimacion.getEsfuerzo() - a - b) * t.getPorcentaje());
		}
	}

	private TareaCronograma getTareaDiseñoTecnico() throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(TipoCosto.ESFUERZO);
		for (TareaCronograma t : mapaTareas.values()) {
			if (t.getDiseñoTecnico().equals(1)) {
				return t;
			}
		}
		throw new ExcepcionCronograma("solamente una tarea de diseño técnico");
	}

	private TareaCronograma getTareaDuracion() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.DURACION);
	}

	private TareaCronograma getTareaFija() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.FIJO);
	}

	private TareaCronograma getTareaUnica(int unTipo) throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(unTipo);
		if (lista.size() != 1) {
			throw new ExcepcionCronograma("solamente una tarea de tipo: " + unTipo);
		}
		TareaCronograma t = lista.iterator().next();
		return t;
	}

	private Set<TareaCronograma> getTareasEsfuerzo() {
		return mapaTareas.get(TipoCosto.ESFUERZO);
	}

	private void init() {
		mapaTareas = HashMultimap.create(4, 4);
		for (TareaCronograma c : estimacion.getTareasCronograma()) {
			mapaTareas.put(c.getTipoCosto(), c);
		}
	}

}
