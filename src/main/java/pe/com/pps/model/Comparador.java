package pe.com.pps.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Comparador {

	private static final Logger log = LogManager.getLogger(Comparador.class);

	public static <T> boolean comparar(Class<T> unaClase, T unObjeto, T otroObjeto, String[] unosAtributos) {
		boolean iguales = true;
		for (String atri : unosAtributos) {
			try {
				Method metodo = unaClase.getMethod("get" + StringUtils.capitalize(atri));
				Object primero = metodo.invoke(unObjeto);
				Object segundo = metodo.invoke(otroObjeto);
				if (primero != null) {
					iguales = iguales && primero.equals(segundo);
				} else {
					iguales = iguales && (segundo == null);
				}
				if (!iguales) {
					break;
				}
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				log.error("al invocar método get para {}", atri);
				iguales = false;
				break;
			}
		}
		return iguales;
	}

	private Comparador() {
		// constructor privado para evitar instanciación
	}

}
