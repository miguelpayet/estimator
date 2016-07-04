package pe.com.pps.ui.estimacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
import pe.com.pps.dao.DaoParametro;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.model.*;
import pe.com.pps.ui.componentes.PaginaBase;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.providers.ProviderActor;
import pe.com.pps.ui.providers.ProviderCasoDeUso;
import pe.com.pps.ui.providers.ProviderCostoAdicional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PaginaEstimacion extends PaginaBase {

	private static final Logger log = LogManager.getLogger(PaginaEstimacion.class);

	private Form campos;
	private Estimacion estimacion;
	private FeedbackPanel feedback;
	private PanelCronograma panelCronograma;
	TextField<Integer> proyecto;

	public PaginaEstimacion() {
		this(null);
	}

	public PaginaEstimacion(final PageParameters unosParametros) {
		super(unosParametros);
		// crear una estimación a partir del parametro
		leerEstimacion(unosParametros);
		if (estimacion == null) {
			nuevaEstimacion();
		}
		agregarTitulo();
		agregarCampos();
		agregarLinks();
		agregarFeedback();
		agregarGridActores();
		agregarGridCasosDeUso();
		agregarCronograma();
		agregarGridCostos();
	}

	private void agregarCampos() {
		campos = new Form("campos");
		add(campos);
		proyecto = new TextField<>("numero", new PropertyModel<>(estimacion, "idEstimacion"));
		proyecto.setOutputMarkupId(true);
		if (estimacion.getIdEstimacion() != null) {
			proyecto.setEnabled(false);
		}
		campos.add(proyecto);
		//TextField<String> eds = new TextField<>("eds", new PropertyModel<>(estimacion, "eds"));
		DaoParametro dp = new DaoParametro();
		DropDownChoice<String> eds = new DropDownChoice<String>("eds", new PropertyModel<>(estimacion, "eds"), dp.getNombreEds());
		campos.add(eds);
		TextField<String> descripcion = new TextField<>("nombre", new PropertyModel<>(estimacion, "nombre"));
		campos.add(descripcion);
	}

	private void agregarCronograma() {
		panelCronograma = new PanelCronograma("cronograma", new Model(estimacion));
		panelCronograma.setOutputMarkupId(true);
		add(panelCronograma);
	}

	private void agregarFeedback() {
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
	}

	private void agregarGridActores() {
		EditableGrid<Actor, String> grid = new EditableGrid<Actor, String>("grid-actores", columnasPuntuable(), new ProviderActor(estimacion), 10, Actor.class) {

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
		grid.setTableCss("actores");
		add(grid);
	}

	private void agregarGridCasosDeUso() {
		EditableGrid<CasoDeUso, String> grid = new EditableGrid<CasoDeUso, String>("grid-casos-de-uso", columnasPuntuable(), new ProviderCasoDeUso(estimacion), 10, CasoDeUso.class) {

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
		grid.setTableCss("casos-de-uso");
		add(grid);
	}

	private void agregarGridCostos() {
		EditableGrid<CostoAdicional, String> grid = new EditableGrid<CostoAdicional, String>("grid-costos", columnasCostos(), new ProviderCostoAdicional(estimacion), 10, CostoAdicional.class) {

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onDelete(AjaxRequestTarget target, IModel<CostoAdicional> rowModel) {
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onSave(AjaxRequestTarget target, IModel<CostoAdicional> rowModel) {
				target.add(feedback);
			}

		};
		grid.setTableCss("costos");
		add(grid);
	}

	private void agregarLinks() {
		AjaxSubmitLink linkGrabar = new AjaxSubmitLink("grabar", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				log.debug("persistir");
				try {
					DaoEstimacion de = new DaoEstimacion();
					de.grabar(estimacion);
					proyecto.setEnabled(false);
					target.add(proyecto);
					success("éxito");
				} catch (Exception e) {
					log.error(e.getMessage());
					error(e.getMessage());
				}
				target.add(feedback);
			}
		};
		add(linkGrabar);
		AjaxSubmitLink linkVolver = new AjaxSubmitLink("volver", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				boolean volver = true;
				log.info("volver");
				// validar si el objeto ha cambiado respecto a lo que está grabado
				if (estimacion.getIdEstimacion() != null && estimacion.getVersion() != null) {
					DaoEstimacion de = new DaoEstimacion();
					Estimacion grabado = de.get(estimacion.getIdEstimacion());
					if (grabado != null) {
						if (!grabado.compararCon(estimacion)) {
							log.info("son distintos");
							error("tienes cambios no grabados. puedes salir, pero no con este botón.");
							volver = false;
							target.add(feedback);
						} else {
							log.info("son iguales");
						}
					} else {
						log.info("no existe");
					}
				} else {
					log.info("es nuevo");
				}
				if (volver) {
					throw new RestartResponseException(PaginaListaEstimaciones.class);
				}
			}
		};
		add(linkVolver);
	}

	private void agregarTitulo() {
		String titulo;
		if (estimacion.getIdEstimacion() == null) {
			titulo = "nueva estimación";
		} else {
			titulo = estimacion.getNombre();
		}
		add(new Label("titulo", titulo));
		add(new Label("page-title", estimacion.getIdEstimacion() + " - " + titulo));
	}

	private List<AbstractEditablePropertyColumn<CostoAdicional, String>> columnasCostos() {
		List<AbstractEditablePropertyColumn<CostoAdicional, String>> columns = new ArrayList<>();
		RequiredEditableTextFieldColumn<CostoAdicional, String> descripcion = new RequiredEditableTextFieldColumn<CostoAdicional, String>(new Model<>("Descripción"), "descripcion") {
			@Override
			protected void addBehaviors(final FormComponent<CostoAdicional> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<String>("descripcion")));
			}
		};
		columns.add(descripcion);
		RequiredEditableTextFieldColumn<CostoAdicional, String> costo = new RequiredEditableTextFieldColumn<CostoAdicional, String>(new Model<>("Costo"), "costo") {
			@Override
			protected void addBehaviors(final FormComponent<CostoAdicional> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<String>("costo")));
			}
		};
		columns.add(costo);
		return columns;
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
		AbstractEditablePropertyColumn<T, String> complejidad = new AbstractEditablePropertyColumn<T, String>(new Model<>("Complejidad"), "complejidadStr") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, Complejidad.getLista());
			}
		};
		columns.add(complejidad);
		DaoPlataforma dp = new DaoPlataforma();
		List<Plataforma> plataformas = dp.listar();
		columns.add(new AbstractEditablePropertyColumn<T, String>(new Model<>("Plataforma"), "plataforma") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, plataformas);
			}
		});
		return columns;
	}

	private Set<CasoDeUso> getCasosDeUso() {
		return estimacion.getCasosDeUso();
	}

	private void leerEstimacion(PageParameters unosParametros) {
		if (unosParametros != null) {
			Integer idEntidad = unosParametros.get("id").toInteger();
			DaoEstimacion de = new DaoEstimacion();
			estimacion = de.get(idEntidad);
			if (estimacion == null) {
				log.error("oops"); // todo: retroalimentar al usuario de alguna forma
			}
		}
	}

	private void nuevaEstimacion() {
		estimacion = EstimacionFactory.crear();
	}

}
