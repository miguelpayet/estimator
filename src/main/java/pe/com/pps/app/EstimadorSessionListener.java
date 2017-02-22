package pe.com.pps.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ISessionListener;
import org.apache.wicket.Session;

public class EstimadorSessionListener implements ISessionListener {

	private static Logger log = LogManager.getLogger(EstimadorSessionListener.class);

	@Override
	public void onCreated(Session session) {

	}

	@Override
	public void onUnbound(String sessionId) {

	}

}
