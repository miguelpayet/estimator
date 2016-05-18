package pe.com.pps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoFactorAmbiental;
import pe.com.pps.dao.DaoFactorTecnico;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;

import java.io.FileInputStream;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEstimacion extends TestBase<Estimacion> {

	public static final Integer ESTIMACION = 11312;

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Test
	public void borrarEstimacion() {
		DaoEstimacion de = new DaoEstimacion();
		{
			Estimacion est = de.get(ESTIMACION);
			if (est != null) {
				// borrarla si ya existe
				de.borrar(est);
			}
		}
	}

	@Test
	public void crearEstimacion() {
		logger.info("crearEstimacion()");
		// nueva estimación
		Estimacion est = new Estimacion();
		est.setIdEstimacion(ESTIMACION);
		est.setEds("Kenji Dettleff");
		est.setNombre("Reporte de Impresión para Cascos");
		// casos de uso
		DaoPlataforma dp = new DaoPlataforma();
		Plataforma p = dp.get(1);
		{
			CasoDeUso cu = new CasoDeUso();
			cu.setComplejidad(Complejidad.ALTA);
			cu.setDescripcion("");
			cu.setNumCaso(1);
			cu.setPlataforma(p);
			est.addCasoDeUso(cu);
		}
		{
			CasoDeUso cu = new CasoDeUso();
			cu.setComplejidad(Complejidad.ALTA);
			cu.setDescripcion("");
			cu.setNumCaso(2);
			cu.setPlataforma(p);
			est.addCasoDeUso(cu);
		}
		// actores
		{
			Actor act = new Actor();
			act.setComplejidad(Complejidad.ALTA);
			act.setDescripcion("Actor 1");
			act.setNumActor(1);
			act.setPlataforma(p);
			est.addActor(act);
		}
		{
			Actor act = new Actor();
			act.setComplejidad(Complejidad.ALTA);
			act.setDescripcion("Actor 2");
			act.setNumActor(2);
			act.setPlataforma(p);
			est.addActor(act);
		}
		// factores
		DaoFactorTecnico dft = new DaoFactorTecnico();
		List<FactorTecnico> factoresTecnicos = dft.getPorTipo(2);
		for (FactorTecnico f : factoresTecnicos) {
			FactorEstimacionTecnico fet = new FactorEstimacionTecnico();
			fet.setFactor(f);
			fet.setValor(1);
			est.addFactorEstimacion(fet);
		}
		DaoFactorAmbiental dfa = new DaoFactorAmbiental();
		List<FactorAmbiental> factoresAmbientales = dfa.getPorTipo(1);
		for (FactorAmbiental f : factoresAmbientales) {
			FactorEstimacionAmbiental fea = new FactorEstimacionAmbiental();
			fea.setFactor(f);
			fea.setValor(1);
			est.addFactorEstimacion(fea);
		}
		// pruebas
		Assert.assertThat(est.getActores().size(), org.hamcrest.CoreMatchers.is(2));
		Assert.assertThat(est.getCasosDeUso().size(), org.hamcrest.CoreMatchers.is(2));
		Assert.assertThat(est.getFactoresEstimacion().size(), org.hamcrest.CoreMatchers.is(factoresTecnicos.size() + factoresAmbientales.size()));
		// grabar
		DaoEstimacion de = new DaoEstimacion();
		de.grabar(est);
		logger.info("grabado");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XlsDataSet(new FileInputStream("datasetEstimacion.xlsx"));
	}

	@Test
	public void leerEstimacion() {
		logger.info("leerEstimacion()");
		// nueva estimación
		Integer numEst = 1;
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(numEst);
		Assert.assertNotNull(est);
		Assert.assertThat(est.getNombre(), org.hamcrest.CoreMatchers.is("Prueba"));
	}

}
