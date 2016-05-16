package pe.com.pps.dao;

import pe.com.pps.model.Actor;
import pe.com.pps.model.ActorPK;

import java.io.Serializable;

public class DaoPK<T, V extends Serializable> extends Dao<T> {

	public DaoPK(Class unaClase) {
		super(unaClase);
	}

	public T get(V unaPk) {
		return (T) getSesion().get(claseModelo, unaPk);
	}

}
