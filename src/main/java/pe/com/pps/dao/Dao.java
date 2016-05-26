package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class Dao<T> {

	protected Class claseModelo;

	public Dao(Class unaClase) {
		claseModelo = unaClase;
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
		Criteria crit = getSesion().createCriteria(getClaseModelo());
		return crit.list();
	}

}
