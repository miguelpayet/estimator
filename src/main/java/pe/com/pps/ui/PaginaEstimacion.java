package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.column.AbstractEditablePropertyColumn;
import org.wicketstuff.egrid.column.EditableCellPanel;
import org.wicketstuff.egrid.column.EditableRequiredDropDownCellPanel;
import org.wicketstuff.egrid.column.RequiredEditableTextFieldColumn;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PaginaEstimacion extends PaginaBase {

	private static final Logger log = LogManager.getLogger(PaginaEstimacion.class);

	private Form campos;
	private FeedbackPanel feedback;
	private Form formCronograma;
	private Estimacion modelo;

	public PaginaEstimacion() {
		this(null);
	}

	public PaginaEstimacion(final PageParameters parameters) {
		super(parameters);
		// todo: código para crear una estimación a partir del parametro
		if (modelo == null) {
			init();
		}
		agregarTitulo(null);
		agregarCampos();
		agregarLinks();
		agregarFeedback();
		agregarGridActores();
		agregarGridCasosDeUso();
		crearFormCronograma();
		agregarFactoresAmbiente();
		agregarFactoresTecnicos();
		agregarCronograma();
	}

	private void agregarCampos() {
		campos = new Form("campos");
		add(campos);
		TextField<Integer> numero = new TextField<>("numero", new PropertyModel<>(modelo, "idEstimacion"));
		campos.add(numero);
		TextField<String> eds = new TextField<>("eds", new PropertyModel<>(modelo, "eds"));
		campos.add(eds);
		TextField<String> descripcion = new TextField<>("nombre", new PropertyModel<>(modelo, "nombre"));
		campos.add(descripcion);
	}

	private void agregarTareaCronograma(RepeatingView unRepetidor, PanelCronograma unPanel, String unaClase ) {
			unPanel.add(new AttributeAppender("class", unaClase));
			unRepetidor.add(unPanel);
	}

	private void agregarCronograma() { // todo: refactorizar este metodo
		RepeatingView rv = new RepeatingView("fila-cronograma");
		rv.setOutputMarkupId(true);
		formCronograma.add(rv);
		Cronograma c = new Cronograma(modelo);
		try {
			agregarTareaCronograma(rv, new PanelTareaFija(rv.newChildId(), new Model<>(c.getTareaFija())), "fija");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea fija");
		}
		try {
			agregarTareaCronograma(rv, new PanelTareaDuracion(rv.newChildId(), new Model<>(c.getTareaDuracion())), "duracion");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea de acompañamiento");
		}
		for (TareaCronograma t : c.getTareasEsfuerzo()) {
			agregarTareaCronograma(rv, new PanelTareaEsfuerzo(rv.newChildId(), new Model<>(t)), "esfuerzo");
			//rv.add(new PanelTareaEsfuerzo(rv.newChildId(), new Model<>(t)));
		}
		try {
			agregarTareaCronograma(rv, new PanelTareaGestion(rv.newChildId(), new Model<>(c.getTareaGestion())), "gestion");
		} catch (ExcepcionCronograma e) {
			log.error("no hay tarea de acompañamiento");
		}
		formCronograma.add(new AjaxSubmitLink("actualizar-cronograma", formCronograma) {
			protected void onSubmit(AjaxRequestTarget target, Form unForm) {
				log.info("actualizar cronograma");
				try {
					modelo.generarCronograma();
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
		for (FactorEstimacion f : modelo.extraerFactores(unTipo)) {
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
		add(feedback);
	}

	private void agregarGridActores() {
		EditableGrid<Actor, String> grid = new EditableGrid<Actor, String>("grid-actores", columnasPuntuable(), new ProviderActor(modelo), 5, Actor.class) {

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onDelete(AjaxRequestTarget target, IModel<Actor> rowModel) {
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onSave(AjaxRequestTarget target, IModel<Actor> rowModel) {
				target.add(feedback);
			}

		};
		add(grid);
	}

	private void agregarGridCasosDeUso() {
		EditableGrid<CasoDeUso, String> grid = new EditableGrid<CasoDeUso, String>("grid-casos-de-uso", columnasPuntuable(), new ProviderCasoDeUso(modelo), 5, CasoDeUso.class) {

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onDelete(AjaxRequestTarget target, IModel<CasoDeUso> rowModel) {
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onSave(AjaxRequestTarget target, IModel<CasoDeUso> rowModel) {
				target.add(feedback);
			}

		};
		add(grid);
	}

	private void agregarLinks() {
		AjaxSubmitLink linkGrabar = new AjaxSubmitLink("grabar", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				log.info("persistir");
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

	// http://stackoverflow.com/questions/13995755/generic-method-in-non-generic-class
	private <T extends Puntuable> List<AbstractEditablePropertyColumn<T, String>> columnasPuntuable() {
		List<AbstractEditablePropertyColumn<T, String>> columns = new ArrayList<>();
		RequiredEditableTextFieldColumn<T, String> descripcion = new RequiredEditableTextFieldColumn<T, String>(new Model<>("Descripción"), "descripcion") {
			@Override
			protected void addBehaviors(final FormComponent<T> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<String>("descripcion")));
			}
		};
		columns.add(descripcion);
		AbstractEditablePropertyColumn<T, String> complejidad = new AbstractEditablePropertyColumn<T, String>(new Model<>("Complejidad"), "complejidad") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, Arrays.asList("1", "2", "3"));
			}
			@Override
			protected void addBehaviors(final FormComponent<T> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<String>("complejidad")));
			}
		};
		columns.add(complejidad);
		DaoPlataforma dp = new DaoPlataforma();
		List<Plataforma> plataformas = dp.listar();
		// todo: esta columna que sea obligatoria
		columns.add(new AbstractEditablePropertyColumn<T, String>(new Model<>("Plataforma"), "plataforma") {
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, plataformas);
			}
		});
		return columns;
	}

	private void crearFormCronograma() {
		formCronograma = new Form<>("form-cronograma", new Model<>(modelo));
		add(formCronograma);
	}

	private Set<CasoDeUso> getCasosDeUso() {
		return modelo.getCasosDeUso();
	}

	private void init() {
		modelo = EstimacionFactory.crear();
	}

}
