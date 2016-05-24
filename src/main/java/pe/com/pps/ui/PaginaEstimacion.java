package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.model.Estimacion;

public class PaginaEstimacion extends PaginaBase {

	private static final Logger log = LogManager.getLogger(PaginaEstimacion.class);
	private Form campos;
	private FeedbackPanel feedback;
	private Estimacion modelo;

	public PaginaEstimacion() {
		super();
		init();
		agregarTitulo(null);
		agregarCampos();
		agregarLinks();
		agregarFeedback();
	}

	public PaginaEstimacion(final PageParameters parameters) {
		super(parameters);
	}

	private void agregarCampos() {
		campos = new Form("campos");
		add(campos);
		TextField<Integer> numero = new TextField("numero", new PropertyModel<Integer>(modelo, "idEstimacion"));
		campos.add(numero);
		TextField<String> eds = new TextField("eds", new PropertyModel<String>(modelo, "eds"));
		campos.add(eds);
		TextField<String> descripcion = new TextField("nombre", new PropertyModel<String>(modelo, "nombre"));
		campos.add(descripcion);
	}

	private void agregarFeedback() {
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
	}

	private void agregarLinks() {
		AjaxSubmitLink linkGrabar = new AjaxSubmitLink("grabar", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				log.info("grabar");
				try {
					DaoEstimacion de = new DaoEstimacion();
					de.grabar(modelo);
					success("éxito");
				} catch (Exception e) {
					log.error(e.getMessage());
					error(e.getMessage());
				}
				target.add(feedback);
			}

		};
		add(linkGrabar);
		AjaxLink linkCaso = new AjaxLink("añadir-caso") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				log.info("añadir caso");
			}
		};
		add(linkCaso);
		AjaxLink linkActor = new AjaxLink("añadir-actor") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				log.info("añadir actor");
			}
		};
		add(linkActor);
	}

	private void agregarTitulo(Estimacion unaEstimacion) {
		String titulo;
		if (unaEstimacion == null) {
			titulo = "nueva estimación";
		} else {
			titulo = unaEstimacion.getNombre();
		}
		add(new Label("titulo", titulo));
	}

	private void init() {
		modelo = new Estimacion();
	}

}
