package pe.trazos.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class HorasRoleCheckingStrategy {

	public boolean hasAnyRole(HorasRoles arg0) {
		Subject s = SecurityUtils.getSubject();
		for (HorasRol r : arg0) {
			if (s.hasRole(r.getNombre())) {
				return true;
			}
		}
		return false;
	}

}
