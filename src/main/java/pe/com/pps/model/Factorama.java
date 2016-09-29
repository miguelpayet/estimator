package pe.com.pps.model;

/**
 * clase que contiene la lógica para separación y cálculo de factores
 */


import com.google.common.collect.HashMultimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Factorama {

	private final static Logger log = LogManager.getLogger(Factorama.class);

	private Estimacion estimacion;
	private Map<Integer, Double> factores;
	private HashMultimap<Integer, FactorEstimacion> mapaFactoresEstimacion;

	public Factorama(Estimacion unaEstimacion) {
		estimacion = unaEstimacion;
		init();
	}

	private Double calcularFactor(List<FactorEstimacion> unosFactores) {
		Double factor = 0.0;
		for (FactorEstimacion f : unosFactores) {
			log.trace("{} - valor {} - peso {} - factor {}", f.getFactor().getNombre(), f.getValor(), f.getFactor().getPeso(), f.getValor() * f.getFactor().getPeso());
			factor += (f.getValor() * f.getFactor().getPeso());
		}
		log.debug("factor de complejidad {}", factor);
		return factor;
	}

	private Double calcularFactorAmbiental() {
		Double factor = calcularFactor(getFactoresAmbientales());
		factor = 1.4 + (factor * -0.03);
		factores.put(TipoFactor.AMBIENTE, factor);
		log.debug("factor ambiental {}", factor);
		return factor;
	}

	private Double calcularFactorTecnico() {
		Double factor = calcularFactor(getFactoresTecnicos());
		factor = 0.6 + (factor * 0.01);
		log.trace("estimación {} - factor técnico {}", estimacion.getIdEstimacion(), factor);
		factores.put(TipoFactor.TECNICO, factor);
		log.debug("factor técnico {}", factor);
		return factor;
	}

	public List<FactorEstimacion> extraerFactores(Integer unTipo) {
		List<FactorEstimacion> lista = mapaFactoresEstimacion.get(unTipo).stream().collect(Collectors.toList());
		java.util.Collections.sort(lista); // todo: sería ideal que esto viniera sorteado por hibernate
		return lista;
	}

	public Double getFactorAmbiental() {
		return factores.get(TipoFactor.AMBIENTE);
	}

	public Double getFactorTecnico() {
		return factores.get(TipoFactor.TECNICO);
	}

	public List<FactorEstimacion> getFactoresAmbientales() {
		return extraerFactores(TipoFactor.AMBIENTE);
	}

	public List<FactorEstimacion> getFactoresTecnicos() {
		return extraerFactores(TipoFactor.TECNICO);
	}

	private void init() {
		mapaFactoresEstimacion = HashMultimap.create(2, 20);
		for (FactorEstimacion f : estimacion.getFactoresEstimacion()) {
			mapaFactoresEstimacion.put(f.getTipo(), f);
		}
		factores = new HashMap<>();
		calcularFactorAmbiental();
		calcularFactorTecnico();
	}

}
