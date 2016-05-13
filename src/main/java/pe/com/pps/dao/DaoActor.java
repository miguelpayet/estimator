package pe.com.pps.dao;

import pe.com.pps.model.Actor;
import pe.com.pps.model.ActorPK;

public class DaoActor extends Dao<Actor> {

	public DaoActor() {
		super(Actor.class);
	}

	public Actor get(ActorPK unaPk) {
		return getSesion().get(Actor.class, unaPk);
	}

}
