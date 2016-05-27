package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
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
import org.wicketstuff.egrid.provider.EditableListDataProvider;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoPlataforma;
import pe.com.pps.dao.HibernateUtil;
import pe.com.pps.model.CasoDeUso;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.Plataforma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
		agregarGridCasosDeUso();
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

	private void agregarGridCasosDeUso() {
		List<AbstractEditablePropertyColumn<CasoDeUso, String>> columnas = columnasCasosDeUso();
		EditableListDataProvider<CasoDeUso, String> provider = getProvider();
		EditableGrid<CasoDeUso, String> grid = new EditableGrid<CasoDeUso, String>("grid-casos-de-uso", columnasCasosDeUso(), new CasoDeUsoProvider(modelo), 5, CasoDeUso.class) {

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

	private List<AbstractEditablePropertyColumn<CasoDeUso, String>> columnasCasosDeUso() {
		List<AbstractEditablePropertyColumn<CasoDeUso, String>> columns = new ArrayList<>();
		columns.add(new RequiredEditableTextFieldColumn<CasoDeUso, String>(new Model<String>("Número"), "numCaso"));
		columns.add(new RequiredEditableTextFieldColumn<CasoDeUso, String>(new Model<String>("Descripción"), "descripcion"));
		columns.add(new AbstractEditablePropertyColumn<CasoDeUso, String>(new Model<String>("Complejidad"), "complejidad") {
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<CasoDeUso, String>(componentId, this, Arrays.asList("1", "2", "3"));
			}
		});
		DaoPlataforma dp = new DaoPlataforma();
		List<Plataforma> plataformas = dp.listar();
		columns.add(new AbstractEditablePropertyColumn<CasoDeUso, String>(new Model<String>("Plataforma"), "plataforma") {
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<CasoDeUso, String>(componentId, this, plataformas);
			}
		});
		return columns;
	}

	private Set<CasoDeUso> getCasosDeUso() {
		return modelo.getCasosDeUso();
	}

	private EditableListDataProvider<CasoDeUso, String> getProvider() {
		return null;
	}

	private void init() {
		modelo = new Estimacion();
	}

}
