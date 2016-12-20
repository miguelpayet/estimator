package pe.com.pps.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import pe.trazos.dao.HibernateUtil;

import java.util.List;

/**
 * clase ligera para acceso a datos de las entidades
 *
 * @param <T> clase de entidad que maneja este dao (para tiempo de compilación)
 */
public abstract class Dao<T> {

	/**
	 * clase del modelo (para tiempo de ejecución)
	 */
	Class claseModelo;

	/**
	 * constructor
	 *
	 * @param unaClase -> clase del modelo
	 */
	Dao(Class unaClase) {
		claseModelo = unaClase;
	}

	/**
	 * crear un objeto criteria para consultas
	 *
	 * @return un criteria
	 */
	Criteria crearCriteria() {
		return getSesion().createCriteria(getClaseModelo());
	}

	/**
	 * eliminar una entidad
	 *
	 * @param unObjeto -> entidad a eliminar
	 */
	public void eliminar(T unObjeto) {
		getSesion().delete(unObjeto);
	}

	/**
	 * obtener una entidad a partir de su llave primaria
	 *
	 * @param unId -> identificador del objeto (típicamente una llave sintética autogenerada)
	 * @return objeto obtenido de la bd
	 */
	@SuppressWarnings("unchecked")
	public T get(int unId) {
		return (T) getSesion().get(claseModelo, unId);
	}

	/**
	 * @return clase de la entidad
	 */
	Class getClaseModelo() {
		return claseModelo;
	}

	/**
	 * @return sesión actual de hibernate
	 */

	Session getSesion() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/**
	 * grabar un objeto a la bd
	 *
	 * @param unObjeto -> objeto a grabar
	 */
	public void grabar(T unObjeto) {
		getSesion().saveOrUpdate(unObjeto);
	}

	/**
	 * listar los objetos de una entidad
	 *
	 * @return lista de objetos de la entidad
	 */
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Criteria crit = crearCriteria();
		return crit.list();
	}

}
