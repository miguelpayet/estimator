package pe.com.pps.ui.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.EstimacionFactory;
import pe.trazos.dao.factory.DaoFactory;

import java.util.Set;

public class PaginaBaseEstimacion extends PaginaBaseEstimador {

	private static final Logger log = LogManager.getLogger(PaginaBaseEstimacion.class);

	protected Label costoTotal;
	private Estimacion estimacion;

	public PaginaBaseEstimacion(final PageParameters unosParametros) {
		super(unosParametros);
		leerEstimacion(unosParametros);
	}

	protected Label agregarCostoTotal() {
		costoTotal = new Label("costo-total", new PropertyModel<Double>(getEstimacion(), "costoTotal"));
		costoTotal.setOutputMarkupId(true);
		add(costoTotal);
		return costoTotal;
	}

	protected void agregarTitulo() {
		String titulo;
		if (getEstimacion().getIdEstimacion() == null) {
			titulo = "nueva estimación";
		} else {
			titulo = getEstimacion().getNombre();
		}
		add(new Label("titulo", titulo));
		super.agregarTitulo((getEstimacion().getNumero() == null ? "" : getEstimacion().getNumero() + " - ") + titulo);
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	protected void leerEstimacion(PageParameters unosParametros) {
		if (unosParametros != null) {
			StringValue parametro = unosParametros.get("id");
			if (parametro.toString() != null) {
				try {
					Integer idEntidad = parametro.toInteger();
					DaoEstimacion de = DaoFactory.getInstance().crearDao(Estimacion.class, DaoEstimacion.class);
					estimacion = de.get(idEntidad);
					if (estimacion == null) {
						log.error("no existe la estimación {}", idEntidad); // todo: retroalimentar al usuario de alguna forma
					}
				} catch (NumberFormatException e) {
					log.error("parámetro inválido {}", parametro.toString());
				}
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
