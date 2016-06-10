package pe.com.pps.ui.estimacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import pe.com.pps.model.*;

public class PanelCronograma extends Panel {

	private static final Logger log = LogManager.getLogger(PanelCronograma.class);
	Cronograma cronograma;
	private FeedbackPanel feedback;
	private Form formCronograma;

	public PanelCronograma(String id, IModel unModelo) {
		super(id, unModelo);
		init(getEstimacion());
		crearFormCronograma();
		agregarFactoresAmbiente();
		agregarFactoresTecnicos();
		agregarCronograma();
		agregarFeedback();
	}

	private void agregarCronograma() { // todo: refactorizar este metodo
		RepeatingView rv = new RepeatingView("fila-cronograma");
		rv.setOutputMarkupId(true);
		formCronograma.add(rv);
		cronograma.setEstimacion(getEstimacion());
		try {
			agregarTareaCronograma(rv, new PanelFilaTareaFija(rv.newChildId(), new Model<>(cronograma.getTareaFija())), "fija");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea fija");
		}
		try {
			agregarTareaCronograma(rv, new PanelFilaTareaDuracion(rv.newChildId(), new Model<>(cronograma.getTareaDuracion())), "duracion");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea de acompañamiento");
		}
		for (TareaCronograma t : cronograma.getTareasEsfuerzo()) {
			agregarTareaCronograma(rv, new PanelFilaTareaEsfuerzo(rv.newChildId(), new Model<>(t)), "esfuerzo");
		}
		try {
			agregarTareaCronograma(rv, new PanelFilaTareaGestion(rv.newChildId(), new Model<>(cronograma.getTareaGestion())), "gestion");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea de acompañamiento");
		}
		PanelFilaTotal pft = new PanelFilaTotal(rv.newChildId(), new Model(cronograma));
		pft.add(new AttributeAppender("class", "totales"));
		rv.add(pft);
		formCronograma.add(new AjaxSubmitLink("actualizar-cronograma", formCronograma) {
			protected void onSubmit(AjaxRequestTarget target, Form unForm) {
				log.info("actualizar cronograma");
				try {
					getEstimacion().generarCronograma();
				} catch (ExcepcionCronograma e) {
					error("error al generar cronograma: " + e.getMessage());
					log.error(e.getMessage());
					e.printStackTrace();
				} finally {
					target.add(formCronograma);
					target.add(feedback);
				}
			}
		});
	}

	private void agregarFactores(String unSufijo, Integer unTipo) {
		RepeatingView rv = new RepeatingView("fila-factor-" + unSufijo);
		rv.setOutputMarkupId(true);
		formCronograma.add(rv);
		for (FactorEstimacion f : getEstimacion().extraerFactores(unTipo)) {
			rv.add(new PanelFactor(rv.newChildId(), new Model<>(f)));
		}
	}

	private void agregarFactoresAmbiente() {
		agregarFactores("ambiente", TipoFactor.AMBIENTE);
	}

	private void agregarFactoresTecnicos() {
		agregarFactores("tecnico", TipoFactor.TECNICO);

	}

	private void agregarFeedback() {
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		formCronograma.add(feedback);
	}

	private void agregarTareaCronograma(RepeatingView unRepetidor, PanelFilaCronograma unPanel, String unaClase) {
		unPanel.add(new AttributeAppender("class", unaClase));
		unRepetidor.add(unPanel);
	}

	private void crearFormCronograma() {
		formCronograma = new Form<>("form-cronograma", new Model<>(getEstimacion()));
		add(formCronograma);
		Label puntosEstimacion = new Label("puntos-ajustados", new PropertyModel<Double>(getEstimacion(), "puntos"));
		//puntosEstimacion.setOutputMarkupId(true);
		formCronograma.add(puntosEstimacion);
		Label horasEstimacion = new Label("horas-esfuerzo", new PropertyModel<Double>(getEstimacion(), "esfuerzo"));
		//horasEstimacion.setOutputMarkupId(true);
		formCronograma.add(horasEstimacion);
		Label meses = new Label("meses-cronograma", new PropertyModel<Double>(cronograma, "totalMeses"));
		//meses.setOutputMarkupId(true);
		formCronograma.add(meses);
	}

	private Estimacion getEstimacion() {
		return (Estimacion) getDefaultModel().getObject();
	}

	private void init(Estimacion unaEstimacion) {
		cronograma = new Cronograma(unaEstimacion);
	}

}
