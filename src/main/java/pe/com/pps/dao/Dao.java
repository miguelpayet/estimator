package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import pe.trazos.dao.HibernateUtil;

import java.util.List;

public class Dao<T> {

	protected Class claseModelo;

	public Dao(Class unaClase) {
		claseModelo = unaClase;
	}

	protected Criteria crearCriteria() {
		return getSesion().createCriteria(getClaseModelo());
	}

	public void eliminar(T unObjeto) {
		getSesion().delete(unObjeto);
	}

	public T get(int unId) {
		return (T) getSesion().get(claseModelo, unId);
	}

	public Class getClaseModelo() {
		return claseModelo;
	}

	protected Session getSesion() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void grabar(T unObjeto) {
		getSesion().saveOrUpdate(unObjeto);
	}

	public List<T> listar() {
		Criteria crit = crearCriteria();
		return crit.list();
	}

	public void persistir(T unObjeto) {
		getSesion().persist(unObjeto);
	}

}
