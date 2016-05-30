package pe.com.pps.model;

import com.google.common.collect.HashMultimap;

import java.io.Serializable;
import java.util.Set;

public class Cronograma implements Serializable {

	private Estimacion estimacion;
	private HashMultimap<Integer, TareaCronograma> mapaTareas;

	public Cronograma(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
		init();
	}

	public void calcular() throws ExcepcionCronograma {
		double a;
		if (getTareaFija().getHoras() != null) {
			a = getTareaFija().getHoras();
		} else {
			throw new ExcepcionCronograma("tarea fija no tiene horas");
		}
		double pctDuracion = getTareaDuracion().getPorcentaje();
		double pctDiseñoTecnico = getTareaDiseñoTecnico().getPorcentaje();
		double b = (pctDuracion * (pctDiseñoTecnico * (estimacion.getEsfuerzo() - a))) / (1 + (pctDuracion * pctDiseñoTecnico));
		getTareaDuracion().setHoras(b);
		for (TareaCronograma t : getTareasEsfuerzo()) {
			t.setHoras((estimacion.getEsfuerzo() - a - b) * t.getPorcentaje());
		}
		getTareaGestion().setDias(getTotalDias());
	}

	private TareaCronograma getTareaDiseñoTecnico() throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(TipoCosto.ESFUERZO);
		for (TareaCronograma t : lista) {
			if (t.getDiseñoTecnico().equals(1)) {
				return t;
			}
		}
		throw new ExcepcionCronograma("no existe tarea de diseño técnico");
	}

	private TareaCronograma getTareaDuracion() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.DURACION);
	}

	public TareaCronograma getTareaFija() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.FIJO);
	}

	private TareaCronograma getTareaGestion() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.GESTION);
	}

	private TareaCronograma getTareaUnica(int unTipo) throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(unTipo);
		if (lista.size() != 1) {
			throw new ExcepcionCronograma("solamente una tarea de tipo: " + unTipo);
		}
		return lista.iterator().next();
	}

	private Set<TareaCronograma> getTareasEsfuerzo() {
		return mapaTareas.get(TipoCosto.ESFUERZO);
	}

	private Double getTotalDias() {
		Double totalDias = 0.0;
		for (TareaCronograma tc : mapaTareas.values()) {
			if (!tc.getTarea().getTipoCosto().equals(TipoCosto.DURACION)) {
				totalDias += tc.getDias();
			}
		}
		return totalDias;
	}

	private Double getTotalHoras() {
		Double totalHoras = 0.0;
		for (TareaCronograma tc : mapaTareas.values()) {
			totalHoras += tc.getHoras();
		}
		return totalHoras;
	}

	private void init() {
		mapaTareas = HashMultimap.create(4, 4);
		for (TareaCronograma c : estimacion.getTareasCronograma()) {
			mapaTareas.put(c.getTipoCosto(), c);
		}
	}

}
