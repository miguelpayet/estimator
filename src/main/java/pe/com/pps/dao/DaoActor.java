package pe.com.pps.dao;

import pe.com.pps.model.Actor;

/**
 * clase para data access de la entidad actor
 */
public class DaoActor extends DaoPK<Actor, Integer> {

	/**
	 * constructor
	 */
	public DaoActor() {
		super(Actor.class);
	}

}
