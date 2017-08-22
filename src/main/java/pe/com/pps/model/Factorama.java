package pe.com.pps.model;

/**
 * clase que contiene la lógica para separación y cálculo de factores
 */


import com.google.common.collect.HashMultimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Factorama {

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
			factor += (f.getValor() * f.getFactor().getPeso());
		}
		return factor;
	}

	private Double calcularFactorAmbiental() {
		Double factor = calcularFactor(getFactoresAmbientales());
		factor = 1.4 + (factor * -0.03);
		factores.put(TipoFactor.AMBIENTE, factor);
		return factor;
	}

	private Double calcularFactorTecnico() {
		Double factor = calcularFactor(getFactoresTecnicos());
		factor = 0.6 + (factor * 0.01);
		factores.put(TipoFactor.TECNICO, factor);
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
