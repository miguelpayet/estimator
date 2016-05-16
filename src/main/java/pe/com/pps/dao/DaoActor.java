package pe.com.pps.dao;

import pe.com.pps.model.Actor;
import pe.com.pps.model.ActorPK;

public class DaoActor extends DaoPK<Actor, ActorPK> {

	public DaoActor() {
		super(Actor.class);
	}

}
