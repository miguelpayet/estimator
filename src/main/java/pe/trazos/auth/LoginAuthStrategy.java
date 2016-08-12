package pe.trazos.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

import java.lang.annotation.Annotation;

public class HorasAuthStrategy implements IAuthorizationStrategy {

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(final Class<T> componentClass) {
		// descartar todos los que no son páginas
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		// páginas con anotación de autentificación
		Annotation[] anns = componentClass.getAnnotations();
		for (Annotation aa : anns) {
			if (aa.annotationType().equals(RequiereAutentificación.class)) {
				return (SecurityUtils.getSubject().isAuthenticated() || SecurityUtils.getSubject().isRemembered());
			}
		}
		return true;
	}

	@Override
	public boolean isResourceAuthorized(IResource resource, PageParameters pageParameters) {
		return true;
	}

}
