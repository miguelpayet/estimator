package pe.com.pps.dao;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class Dao<T> {

	private Class claseModelo;

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
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		if (sesion.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			sesion.beginTransaction();
		}
		return sesion;
	}

	public void grabar(T unObjeto) {
		getSesion().persist(unObjeto);
	}

}
