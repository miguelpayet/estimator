package pe.com.pps.dao;

import pe.com.pps.model.Tarea;
import pe.trazos.dao.Dao;
import pe.trazos.dao.factory.DataAccessObject;

@DataAccessObject(Tarea.class)
public class DaoTarea extends Dao<Tarea> {

	public DaoTarea() {
		super();
	}

}
