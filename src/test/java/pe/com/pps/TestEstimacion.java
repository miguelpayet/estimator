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
import pe.com.pps.dao.DaoFactor;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;

import java.io.FileInputStream;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEstimacion extends TestBase<Estimacion> {

	private final Logger logger = LogManager.getLogger(TestBase.class);

	@Test
	public void crearEstimacion() {
		// nueva estimación
		Integer numEst = 11312;
		Estimacion est = new Estimacion();
		est.setIdEstimacion(numEst);
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
		DaoFactor df = new DaoFactor();
		List<Factor> factores = df.getAll();
		for (Factor f : factores) {
			FactorEstimacion fe = new FactorEstimacion();
			fe.setFactor(f);
			fe.setValor(1);
			est.addFactorEstimacion(fe);
		}
		// grabar
		DaoEstimacion de = new DaoEstimacion();
		de.grabar(est);
		logger.info("grabado");
		de.borrar(est);
		logger.info("y luego borrado");
		// pruebas
		Assert.assertThat(est.getActores().size(), org.hamcrest.CoreMatchers.is(2));
		Assert.assertThat(est.getCasosDeUso().size(), org.hamcrest.CoreMatchers.is(2));
		Assert.assertThat(est.getFactoresEstimacion().size(), org.hamcrest.CoreMatchers.is(factores.size()));
		logger.info("crearEstimacion()");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XlsDataSet(new FileInputStream("datasetEstimacion.xlsx"));
	}

	@Test
	public void leerEstimacion() {
		// nueva estimación
		Integer numEst = 1;
		DaoEstimacion de = new DaoEstimacion();
		Estimacion est = de.get(numEst);
		Assert.assertNotNull(est);
		logger.info("estimación es: " + est.getNombre());
		logger.info("leerEstimacion()");
	}

}
