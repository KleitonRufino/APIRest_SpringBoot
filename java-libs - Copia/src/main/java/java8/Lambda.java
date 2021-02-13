//***********************************************************************
//
//	Cap�tulo 2 - Ol� Lambda!
//
//***********************************************************************

package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Lambda {

	public static void main(String ... args) {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

 		// Imprimir o nome de todos os usu�rios - Vers�o 1
		for(Usuario u : usuarios) {
			System.out.println(u.getNome());
		}

 		// Imprimir o nome de todos os usu�rios - Vers�o 2
		Consumer<Usuario> mostrador1 = new Consumer<Usuario>() {
			public void accept(Usuario u) {
				System.out.println(u.getNome());
			}
		};

 		usuarios.forEach(mostrador1);

 		// Imprimir o nome de todos os usu�rios - Vers�o 3
		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario u) {
				System.out.println(u.getNome());
			}
		});

 		// Imprimir o nome de todos os usu�rios - Vers�o 4
		Consumer<Usuario> mostrador2 = (Usuario u) -> System.out.println(u.getNome());

 		// Imprimir o nome de todos os usu�rios - Vers�o 5
		Consumer<Usuario> mostrador3 = u -> { System.out.println(u.getNome()); };

 		// Imprimir o nome de todos os usu�rios - Vers�o 5
		Consumer<Usuario> mostrador4 = u -> System.out.println(u.getNome());

 		// Imprimir o nome de todos os usu�rios - Vers�o 7
		usuarios.forEach(u -> System.out.println(u.getNome()));

 		// Tornar todos os usu�rios moderadores
		usuarios.forEach(u -> u.tornaModerador());
	}
}
