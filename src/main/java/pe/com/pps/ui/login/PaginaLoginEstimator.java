package pe.com.pps.ui.login;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import pe.com.pps.model.Util;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.login.PanelLogin;

import java.io.IOException;
import java.util.Properties;

public class PaginaLoginEstimator extends PaginaBaseEstimador {

	public PaginaLoginEstimator() {
		this(new PageParameters());
	}

	public PaginaLoginEstimator(PageParameters parameters) {
		super(parameters);
		agregarTitulo("login estimador");
		agregarLogo();
		agregarPanelLogin();
		agregarBuildNumber();
	}

	private void agregarBuildNumber() {
		String buildNumber;
		try {
			Properties prop = Util.loadProperties("buildNumber.properties");
			buildNumber = prop.getProperty("buildNumber");
		} catch (IOException e) {
			buildNumber = "desconocido";
		} add(new Label("build-number", new Model<>(buildNumber)));
	}

	private void agregarLogo() {
		add(new Image("imagen-login", new ContextRelativeResource("estimator.png")));
	}

	private void agregarPanelLogin() {
		PanelLogin pl = new PanelLogin("panel-login", PaginaSolicitudNuevoPasswordEstimator.class);
		add(pl);
	}

}
