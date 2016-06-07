package pe.com.pps.ui.providers;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import pe.com.pps.dao.HibernateUtil;

import java.util.List;

public class QueryCriteria<T> {

	private Criteria criteria;

	public QueryCriteria(Class queryClass) {
		createCriteria(queryClass);
	}

	public long contarFilas() {
		long filas;
		criteria.setProjection(Projections.rowCount());
		Object resultadoQuery = criteria.uniqueResult();
		//getSql();
		if (resultadoQuery != null) {
			filas = (long) resultadoQuery;
		} else {
			filas = 0;
		}
		return filas;
	}

	private void createCriteria(Class queryClass) {
		criteria = getSession().createCriteria(queryClass);
	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unused")
	private void getSql() {
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
		SessionImplementor session = criteriaImpl.getSession();
		SessionFactoryImplementor factory = session.getFactory();
		CriteriaQueryTranslator translator = new CriteriaQueryTranslator(factory, criteriaImpl, criteriaImpl.getEntityOrClassName(), CriteriaQueryTranslator.ROOT_SQL_ALIAS);
		String[] implementors = factory.getImplementors(criteriaImpl.getEntityOrClassName());
		CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable) factory.getEntityPersister(implementors[0]), translator, factory, criteriaImpl, criteriaImpl.getEntityOrClassName(), session.getLoadQueryInfluencers());
		String sql = walker.getSQLString();
	}

	@SuppressWarnings("unchecked")
	public List<T> leer() {
		return criteria.list();
	}

	public void setFiltro(String campo, String valor) {
		if (campo != null && !campo.equals("") && valor != null && !valor.equals("")) {
			criteria.add(Restrictions.like(campo, "%" + valor.trim() + "%"));
		}
	}

	public void setOrden() {
	}

	private void setRango(int inicio, int count) {
		criteria.setFirstResult(inicio);
		criteria.setMaxResults(count);
	}

	public void setRango(long inicio, long count) {
		setRango((int) inicio, (int) count);
	}

}

