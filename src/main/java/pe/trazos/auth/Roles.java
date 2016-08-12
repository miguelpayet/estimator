package pe.trazos.auth;

import java.util.ArrayList;
import java.util.Iterator;

public class HorasRoles implements Iterable<HorasRol> {

	public final static String ADMIN = "ADMIN";

	ArrayList<HorasRol> roles;

	public HorasRoles() {
	}

	public Iterator<HorasRol> iterator() {
		return roles.iterator();
	}

}
