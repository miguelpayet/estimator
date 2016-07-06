package pe.com.pps.ui.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.EstimacionFactory;

import java.util.Set;

public class PaginaBaseEstimacion extends PaginaBase {

	private static final Logger log = LogManager.getLogger(PaginaBaseEstimacion.class);
	private Estimacion estimacion;

	public PaginaBaseEstimacion(final PageParameters unosParametros) {
		super(unosParametros);
		leerEstimacion(unosParametros);
	}

	protected void agregarTitulo() {
		String titulo;
		if (getEstimacion().getIdEstimacion() == null) {
			titulo = "nueva estimación";
		} else {
			titulo = getEstimacion().getNombre();
		}
		add(new Label("titulo", titulo));
		super.agregarTitulo(getEstimacion().getIdEstimacion() + " - " + titulo);
	}

	private Set<CasoDeUso> getCasosDeUso() {
		return getEstimacion().getCasosDeUso();
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	protected void leerEstimacion(PageParameters unosParametros) {
		if (unosParametros != null) {
			Integer idEntidad = unosParametros.get("id").toInteger();
			DaoEstimacion de = new DaoEstimacion();
			estimacion = de.get(idEntidad);
			if (estimacion == null) {
				log.error("oops"); // todo: retroalimentar al usuario de alguna forma
			}
		}
	}

	protected void nuevaEstimacion() {
		estimacion = EstimacionFactory.crear();
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

}
