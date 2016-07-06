package pe.com.pps.model;

import com.google.common.collect.HashMultimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.pps.dao.DaoParametro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * clase ligera para calcular el cronograma de una estimación
 * lo único que hace es modificar las tareas de la estimación de acuerdo a las reglas definidas
 */
public class Cronograma implements Serializable {

	private final static Logger log = LogManager.getLogger(Cronograma.class);

	private Estimacion estimacion;
	private HashMultimap<Integer, TareaCronograma> mapaTareas;

	public Cronograma(Estimacion unaEstimacion) {
		setEstimacion(unaEstimacion);
	}

	/**
	 * calcular el cronograma - tiene cuatro tipos de tarea:
	 * tarea fija: se asigna manualmente un esfuerzo en horas. se resta del total de horas del cronograma
	 * tarea x duración: tarea que equivale a un porcentaje del esfuerzo de diseño técnico
	 * tareas x esfuerzo: se asigna un porcentaje del total de horas del cronograma
	 * tarea de gestión: % de la duración completa del cronograma - sumado al total de horas.
	 * modifica directamente las tareas de la estimación.
 	 */
	public void calcular() throws ExcepcionCronograma {
		// tarea fija (ids)
		double tareaFija;
		if (getTareaFija().getHoras() != null) {
			tareaFija = getTareaFija().getHoras();
		} else {
			throw new ExcepcionCronograma("tarea fija no tiene horas");
		}
		log.trace("tarea fija - esfuerzo {}", tareaFija);
		double pctDuracion = getTareaDuracion().getPorcentaje();
		double pctDiseñoTecnico = getTareaDiseñoTecnico().getPorcentaje();
		// tarea duración (acompañamiento al diseño
		double tareaDuracion = Util.round((pctDuracion * pctDiseñoTecnico * (estimacion.getEsfuerzo() - tareaFija)) / (1 + (pctDuracion * pctDiseñoTecnico)), 2);
		log.trace("tarea duración - esfuerzo {}", tareaDuracion);
		getTareaDuracion().setHoras(tareaDuracion);
		// tareas por esfuerzo: pueden estar o no incluidas
		// validar que sume 100% entre que las estén activas
		double pctIncluidas = 0;
		for (TareaCronograma t : getTareasEsfuerzo()) {
			log.trace("tarea {} - porcentaje {}", t, Util.round(t.getPorcentaje(), 2));
			if (t.getIncluir()) {
				pctIncluidas += t.getPorcentaje();
			}
		}
		if (Util.round(pctIncluidas, 2) != 1) {
			throw new ExcepcionCronograma("tareas incluidas no suman 100%, suman " + Util.round(pctIncluidas * 100, 2) + "%");
		}
		// calcular las tareas x esfuerzo
		for (TareaCronograma t : getTareasEsfuerzo()) {
			if (t.getIncluir()) {
				t.setHoras(Util.round((estimacion.getEsfuerzo() - tareaFija - tareaDuracion) * t.getPorcentaje(), 2));
			} else {
				t.setHoras(0.0);
			}
		}
		// tarea de gestión
		getTareaGestion().setDias(getTotalDias());
		log.trace("tarea gestión - esfuerzo {}", getTareaGestion().getHoras());
	}

	/**
	 * revisa las tareas del cronograma, multiplica las horas * el costo
	 * y arma un mapa con el costo por proveedor
	 *
	 * @return costo del cronograma por proveedor en un mapa indexado por proveedor
	 */
	public List<CostoProveedor> getCostoProveedores() {
		// primero hago un mapa de proveedor / costoproveedor en blanco
		Map<Proveedor, CostoProveedor> costos = new HashMap<>();
		// despues paso por la lista de tareas
		for (TareaCronograma t : mapaTareas.values()) {
			// si ya tengo este proveedor en el mapa agarro su costoproveedor si no, creo uno nuevo y lo coloco en el mapa
			CostoProveedor cp;
			if (t.getProveedor() != null) {
				if (costos.containsKey(t.getProveedor())) {
					cp = costos.get(t.getProveedor());
				} else {
					cp = new CostoProveedor(t.getProveedor());
					costos.put(t.getProveedor(), cp);
				}
				// sumo el costo de la tarea dentro del costoproveedor
				cp.sumarCosto(t.getCosto());
			}
		}
		// devuelvo el valueset del mapa como lista
		return costos.values().stream().collect(Collectors.toList());
	}

	/**
	 * método interno para obtener el porcentaje de desviación
	 *
	 * @return valor del parámetro de desviación
	 */
	private Double getRangoDesviacion() {
		DaoParametro dp = new DaoParametro();
		Parametro p = dp.getDesviacion();
		return p.getValorDouble();
	}

	/**
	 * método para wicket.
	 *
	 * @return el máximo de horas de la estimación según el parámetro de desviación
	 */
	public Double getRangoMaximo() {
		Double horas = getTotalHoras();
		return Util.round(horas + (horas * getRangoDesviacion()), 2);
	}

	/**
	 * método para wicket.
	 *
	 * @return el mínimo de horas de la estimación según el parámetro de desviación
	 */
	public Double getRangoMinimo() {
		Double horas = getTotalHoras();
		return Util.round(horas - (horas * getRangoDesviacion()), 2);
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

	public Double getTotalDias() {
		Double totalDias = 0.0;
		for (TareaCronograma tc : mapaTareas.values()) {
			if (!tc.getTarea().getTipoCosto().equals(TipoCosto.DURACION) && !tc.getTarea().getTipoCosto().equals(TipoCosto.GESTION)) {
				totalDias += tc.getDias();
			}
		}
		return totalDias;
	}

	public Double getTotalHoras() {
		Double totalHoras = 0.0;
		for (TareaCronograma tc : mapaTareas.values()) {
			totalHoras += tc.getHoras();
		}
		return totalHoras;
	}

	public Double getTotalMeses() {
		return Util.round(getTotalDias() / 30, 2);
	}

	/**
	 * separa las tareas de la estimación según su tipo, en un multimapa
	 * las tareas siguen estando referenciadas directamente desde la estimación
	 */
	private void init() {
		mapaTareas = HashMultimap.create(4, 4);
		for (TareaCronograma c : estimacion.getTareasCronograma()) {
			mapaTareas.put(c.getTipoCosto(), c);
		}
	}

	/**
	 * copia la referencia a la estimación recibida en @param unaEstimacion e inicializa el cronograma
	 */
	public void setEstimacion(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
		init();
	}

}
