package java8;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Grupo {

	private Set<Usuario> usuarios = new HashSet<>();
	
	public void add(Usuario u) {
		usuarios.add(u);
	}
	
	public Set<Usuario> getUsuario() {
		return Collections.unmodifiableSet(this.usuarios);
	}
}
