package pe.com.pps.ui.estimacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import pe.com.pps.model.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class PanelCronograma extends PanelBaseCronograma {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger(PanelCronograma.class);

	private FeedbackPanel feedback;
	private Form<Estimacion> formCronograma;

	PanelCronograma(String id, IModel<Estimacion> unModelo) {
		super(id, unModelo);
		crearFormCronograma();
		agregarFactoresAmbiente();
		agregarFactoresTecnicos();
		agregarCronograma();
		agregarAccionCalcular();
		agregarPanelResumen();
		agregarFeedback();
	}

	private void agregarAccionCalcular() {
		formCronograma.add(new AjaxSubmitLink("actualizar-cronograma", formCronograma) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				try {
					// generar el cronograma de la estimación
					getEstimacion().generarCronograma();
					// obtener la página a la que pertenece el panel y refrescar los componentes registrados
					PaginaEstimacion pe = findParent(PaginaEstimacion.class);
					pe.getTargets().forEach(target::add);
				} catch (ExcepcionCronograma e) {
					// logear el error
					log.error(e.getMessage(), e);
					// mensaje en panel de feedback
					error("error al generar cronograma: " + e.getMessage());
					// mensaje como cuadro de diálogo
					target.appendJavaScript("alert('" + e.getMessage() + "')");
				} finally {
					target.add(formCronograma);
					target.add(feedback);
				}
			}
		});
	}

	private void agregarCronograma() {
		RepeatingView rv = new RepeatingView("fila-cronograma");
		rv.setOutputMarkupId(true);
		formCronograma.add(rv);
		cronograma.setEstimacion(getEstimacion());
		List<TareaCronograma> tareas = getEstimacion().getTareasCronograma();
		tareas.sort(TareaCronograma::compareTo);
		for (TareaCronograma t : tareas) {
			agregarTareaCronograma(rv, t);
		}
		PanelFilaTotal pft = new PanelFilaTotal(rv.newChildId(), new Model<>(cronograma));
		pft.add(new AttributeAppender("class", "totales"));
		rv.add(pft);
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

	private void agregarPanelResumen() {
		formCronograma.add(new PanelResumen("panel-resumen", new Model<>(getEstimacion())));
	}

	/**
	 * agregar una tarea al repeatingview del cronograma
	 *
	 * @param unRepetidor        -> repeatingview para el cronograma
	 * @param unaTareaCronograma -> tarea cuyo panel toca añadir
	 */
	private void agregarTareaCronograma(RepeatingView unRepetidor, TareaCronograma unaTareaCronograma) {
		try {
			// obtener la tarea
			Tarea t = unaTareaCronograma.getTarea();
			// obtener la clase de panel que usa la tarea y construirlo
			Class<? extends PanelFilaCronograma> panelFila = t.getClasePanel();
			Constructor<? extends PanelFilaCronograma> ctor = panelFila.getConstructor(String.class, IModel.class);
			PanelFilaCronograma panel = (PanelFilaCronograma) ctor.newInstance(unRepetidor.newChildId(), new Model<>(unaTareaCronograma));
			// añadir el estilo css de la fila
			panel.add(new AttributeAppender("class", t.getClaseCss()));
			// añadir el panel al repetidor
			panel.setOutputMarkupId(true);
			unRepetidor.add(panel);
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			log.error("error al construir fila del panel " + e.getMessage());
		}
	}

	private void crearFormCronograma() {
		formCronograma = new Form<Estimacion>("form-cronograma", new Model<>(getEstimacion()));
		add(formCronograma);
	}

}
