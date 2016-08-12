package pe.trazos.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.trazos.dao.DaoUsuario;
import pe.trazos.dominio.Rol;
import pe.trazos.dominio.Usuario;

public class HorasShiroRealm extends AuthorizingRealm {

	private final Logger logger = LoggerFactory.getLogger(HorasShiroRealm.class);

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("HorasShiroRealm.doGetAuthenticationInfo " + token.getPrincipal());
		Usuario u;
		DaoUsuario du = new DaoUsuario();
		SimpleAuthenticationInfo info = null;
		u = du.getPorCodigo((String) token.getPrincipal());
		if (u != null) {
			Sha256Hash hash = Sha256Hash.fromBase64String(u.getPassword());
			info = new SimpleAuthenticationInfo(u.getCodigo(), hash, getName());
			ByteSource bytes = u.getSalt();
			info.setCredentialsSalt(bytes);
		}
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("HorasShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		DaoUsuario du = new DaoUsuario();
		Usuario u = du.getPorCodigo((String) principals.getPrimaryPrincipal());
		if (u != null) {
			for (Rol r : u.getRoles()) {
				info.addRole(r.getNombre());
			}
		}
		return info;
	}

}
