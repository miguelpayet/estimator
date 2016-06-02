package pe.com.pps.model;

import java.io.Serializable;

public interface Identificable<T extends Serializable> {

	public T getId();

}
