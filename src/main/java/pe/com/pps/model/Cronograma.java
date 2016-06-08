package pe.com.pps.model;

import com.google.common.collect.HashMultimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cronograma implements Serializable {

	private final static Logger log = LogManager.getLogger(Cronograma.class);

	private Estimacion estimacion;
	private HashMultimap<Integer, TareaCronograma> mapaTareas;

	public Cronograma(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
		init();
	}

	// todo: agregar lógica para excluir tareas
	public void calcular() throws ExcepcionCronograma {
		double tareaFija;
		if (getTareaFija().getHoras() != null) {
			tareaFija = getTareaFija().getHoras();
		} else {
			throw new ExcepcionCronograma("tarea fija no tiene horas");
		}
		double pctDuracion = getTareaDuracion().getPorcentaje();
		double pctDiseñoTecnico = getTareaDiseñoTecnico().getPorcentaje();
		double tareaDuracion = (pctDuracion * (pctDiseñoTecnico * (estimacion.getEsfuerzo() - tareaFija))) / (1 + (pctDuracion * pctDiseñoTecnico));
		getTareaDuracion().setHoras(tareaDuracion);
		// estas tareas pueden estar o no incluidas
		// validar que sume 100% entre que estén activas
		double pctIncluidas = 0;
		for (TareaCronograma t : getTareasEsfuerzo()) {
			log.trace("tarea {} - porcentaje {}", t, Util.round(t.getPorcentaje(), 2));
			if (t.getIncluir()) {
				pctIncluidas += Util.round(t.getPorcentaje(), 2);
			}
		}
		if (Util.round(pctIncluidas, 2) != 1) {
			throw new ExcepcionCronograma("tareas incluidas no suman 100%, suman " + pctIncluidas * 100 + "%");
		}
		for (TareaCronograma t : getTareasEsfuerzo()) {
			if (t.getIncluir()) {
				t.setHoras((estimacion.getEsfuerzo() - tareaFija - tareaDuracion) * Util.round(t.getPorcentaje(), 2));
			} else {
				t.setHoras(0.0);
			}
		}
		getTareaGestion().setDias(getTotalDias());
	}

	public TareaCronograma getTareaDiseñoTecnico() throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(TipoCosto.ESFUERZO);
		for (TareaCronograma t : lista) {
			if (t.getDiseñoTecnico().equals(1)) {
				return t;
			}
		}
		throw new ExcepcionCronograma("no existe tarea de diseño técnico");
	}

	public TareaCronograma getTareaDuracion() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.DURACION);
	}

	public TareaCronograma getTareaFija() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.FIJO);
	}

	public TareaCronograma getTareaGestion() throws ExcepcionCronograma {
		return getTareaUnica(TipoCosto.GESTION);
	}

	private TareaCronograma getTareaUnica(int unTipo) throws ExcepcionCronograma {
		Set<TareaCronograma> lista = mapaTareas.get(unTipo);
		if (lista.size() != 1) {
			throw new ExcepcionCronograma("solamente una tarea de tipo: " + unTipo);
		}
		return lista.iterator().next();
	}

	public List<TareaCronograma> getTareasEsfuerzo() {
		List<TareaCronograma> lista = mapaTareas.get(TipoCosto.ESFUERZO).stream().collect(Collectors.toList());
		java.util.Collections.sort(lista); // todo: sería ideal que esto viniera sorteado por hibernate
		return lista;
	}

	private Double getTotalDias() {
		Double totalDias = 0.0;
		for (TareaCronograma tc : mapaTareas.values()) {
			if (!tc.getTarea().getTipoCosto().equals(TipoCosto.DURACION) && !tc.getTarea().getTipoCosto().equals(TipoCosto.GESTION)) {
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
