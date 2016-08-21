package pe.com.pps.ui.login;


import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import pe.com.pps.ui.base.PaginaBaseEstimador;
import pe.trazos.login.ui.login.PanelLogin;

public class PaginaLoginEstimator extends PaginaBaseEstimador {

	public PaginaLoginEstimator() {
		this(new PageParameters());
	}

	public PaginaLoginEstimator(PageParameters parameters) {
		super(parameters);
		agregarTitulo("login estimador");
		agregarLogo();
		agregarPanelLogin();
	}

	private void agregarPanelLogin() {
		add(new PanelLogin("panel-login"));
	}

	private void agregarLogo() {
		add(new Image("imagen-login", new ContextRelativeResource("estimator.png")));
	}

}
