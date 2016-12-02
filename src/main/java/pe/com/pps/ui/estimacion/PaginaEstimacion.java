package pe.com.pps.ui.estimacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.wicketstuff.egrid.EditableGrid;
import org.wicketstuff.egrid.column.AbstractEditablePropertyColumn;
import org.wicketstuff.egrid.column.EditableCellPanel;
import org.wicketstuff.egrid.column.EditableRequiredDropDownCellPanel;
import org.wicketstuff.egrid.column.RequiredEditableTextFieldColumn;
import pe.com.pps.dao.DaoEstimacion;
import pe.com.pps.dao.DaoParametro;
import pe.com.pps.model.CostoAdicional;
import pe.com.pps.model.Estimacion;
import pe.com.pps.model.ExcepcionCronograma;
import pe.com.pps.model.Moneda;
import pe.com.pps.ui.base.PaginaBaseEstimacion;
import pe.com.pps.ui.listaestimaciones.PaginaListaEstimaciones;
import pe.com.pps.ui.providers.ProviderCostoAdicional;
import pe.com.pps.ui.vista.PaginaVistaEstimacion;
import pe.trazos.dao.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AuthorizeInstantiation("usuario")
public class PaginaEstimacion extends PaginaBaseEstimacion {

	private static final Logger log = LogManager.getLogger(PaginaEstimacion.class);

	private Form campos;
	private FeedbackPanel feedback;
	private Set<Component> targets;

	@SuppressWarnings("unused")
	public PaginaEstimacion() {
		this(new PageParameters());
	}

	/**
	 * constructor que recibe el id de estimación
	 *
	 * @param unosParametros contiene un parámetro tipo id -> número
	 */
	public PaginaEstimacion(final PageParameters unosParametros) {
		super(unosParametros);
		// conjunto de elementos que hay que refrescar cada vez que se calcule
		targets = new HashSet<>();
		// leer la estimación del parametro
		leerEstimacion(unosParametros);
		// si la estimación del parámetro no existe (o no había estimación en el parámetro) crear una nueva estimación
		if (getEstimacion() == null) {
			nuevaEstimacion();
		}
		agregarTitulo();
		agregarCampos();
		agregarLinks();
		agregarFeedback();
		agregarPanelesPuntuables();
		agregarCronograma();
		agregarPanelCostos();
		agregarGridCostos();
		agregarCostoTotal();
	}

	private void agregarCampos() {
		campos = new Form("campos");
		add(campos);
		TextField proyecto = new TextField<>("numero", new PropertyModel<>(getEstimacion(), "idEstimacion"));
		proyecto.setOutputMarkupId(true);
		if (getEstimacion().getIdEstimacion() != null) {
			proyecto.setEnabled(false);
		}
		campos.add(proyecto);
		DaoParametro dp = new DaoParametro();
		DropDownChoice<String> eds = new DropDownChoice<>("eds", new PropertyModel<>(getEstimacion(), "eds"), dp.getNombreEds());
		campos.add(eds);
		TextField<String> descripcion = new TextField<>("nombre", new PropertyModel<>(getEstimacion(), "nombre"));
		campos.add(descripcion);
	}

	protected Label agregarCostoTotal() {
		Label costoTotal = super.agregarCostoTotal();
		targets.add(costoTotal);
		return costoTotal;
	}

	private void agregarCronograma() {
		PanelCronograma panelCronograma = new PanelCronograma("cronograma", new Model<>(getEstimacion()));
		panelCronograma.setOutputMarkupId(true);
		add(panelCronograma);
	}

	private void agregarFeedback() {
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
	}

	private void agregarGridCostos() {
		EditableGrid<CostoAdicional, String> grid = new EditableGrid<CostoAdicional, String>("grid-costos", columnasCostos(), new ProviderCostoAdicional(getEstimacion()), 10, CostoAdicional.class) {

			@Override
			protected void onAdd(AjaxRequestTarget target, CostoAdicional newRow) {
				target.add(costoTotal);
			}

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onDelete(AjaxRequestTarget target, IModel<CostoAdicional> rowModel) {
				target.add(feedback);
				target.add(costoTotal);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(feedback);
			}

			@Override
			protected void onSave(AjaxRequestTarget target, IModel<CostoAdicional> rowModel) {
				target.add(feedback);
				target.add(costoTotal);
			}

		};
		grid.setTableCss("costos");
		add(grid);
	}

	private void agregarLinks() {
		SubmitLink linkGrabar = new SubmitLink("grabar", campos) {
			@Override
			public void onSubmit() {
				try {
					log.info("grabar estimacion {}", getEstimacion().getIdEstimacion());
					Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();
					if (tx.getStatus() == TransactionStatus.ACTIVE) {
						DaoEstimacion de = new DaoEstimacion();
						getEstimacion().generarCronograma();
						de.grabar(getEstimacion());
						tx.commit();
						PageParameters params = new PageParameters();
						params.add("id", getEstimacion().getIdEstimacion());
						throw new RestartResponseException(PaginaEstimacion.class, params);
					} else {
						error("no está activa la transacción hibernate");
					}
				} catch (ExcepcionCronograma e) {
					error(e.getMessage());
				}
			}
		};
		add(linkGrabar);
		AjaxSubmitLink linkVolver = new AjaxSubmitLink("volver", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				boolean volver = true;
				// validar si el objeto ha cambiado respecto a lo que está grabado
				if (getEstimacion().getIdEstimacion() != null && getEstimacion().getVersion() != null) {
					DaoEstimacion de = new DaoEstimacion();
					Estimacion grabado = de.get(getEstimacion().getIdEstimacion());
					if (grabado != null) {
						if (!grabado.compararCon(getEstimacion())) {
							error("tienes cambios no grabados. puedes salir, pero no con este botón.");
							volver = false;
							target.add(feedback);
						}
					}
				}
				if (volver) {
					throw new RestartResponseException(PaginaListaEstimaciones.class);
				}
			}
		};
		add(linkVolver);
		AjaxSubmitLink linkImprimir = new AjaxSubmitLink("imprimir", campos) {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				PageParameters p = new PageParameters();
				p.add("id", getEstimacion().getIdEstimacion());
				throw new RestartResponseException(PaginaVistaEstimacion.class, p);
			}
		};
		add(linkImprimir);
	}

	private void agregarPanelCostos() {
		PanelCostos pc = new PanelCostos("panel-costos", new Model<>(getEstimacion()));
		pc.setOutputMarkupId(true);
		add(pc);
		targets.add(pc);
	}

	private void agregarPanelesPuntuables() {
		add(new PanelCasosDeUso("panel-casos-de-uso", getEstimacion(), feedback));
		add(new PanelActores("panel-actores", getEstimacion(), feedback));
	}

	private List<AbstractEditablePropertyColumn<CostoAdicional, String>> columnasCostos() {
		List<AbstractEditablePropertyColumn<CostoAdicional, String>> columns = new ArrayList<>();
		RequiredEditableTextFieldColumn<CostoAdicional, String> descripcion = new RequiredEditableTextFieldColumn<CostoAdicional, String>(new Model<>("Descripción"), "descripcion") {
			@Override
			protected void addBehaviors(final FormComponent<CostoAdicional> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<>("descripcion")));
			}
		};
		columns.add(descripcion);
		AbstractEditablePropertyColumn<CostoAdicional, String> moneda = new AbstractEditablePropertyColumn<CostoAdicional, String>(new Model<>("Moneda"), "moneda") {
			@Override
			public EditableCellPanel getEditableCellPanel(String componentId) {
				return new EditableRequiredDropDownCellPanel<>(componentId, this, Moneda.getLista());
			}
		};
		columns.add(moneda);
		RequiredEditableTextFieldColumn<CostoAdicional, String> costo = new RequiredEditableTextFieldColumn<CostoAdicional, String>(new Model<>("Costo"), "costo") {
			@Override
			protected void addBehaviors(final FormComponent<CostoAdicional> editorComponent) {
				super.addBehaviors(editorComponent);
				editorComponent.add(new AttributeModifier("class", new Model<>("costo")));
			}
		};
		columns.add(costo);
		return columns;
	}

	/**
	 * retorna la lista de targets que se debe actualizar cuando se calcula el panel cronograma
	 *
	 * @return lista de targets para refresh via ajax
	 */
	Set<Component> getTargets() {
		return targets;
	}

}
