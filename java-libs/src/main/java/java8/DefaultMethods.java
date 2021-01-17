//***********************************************************************
//
//	Cap�tulo 4 - Default Methods
//
//***********************************************************************

package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultMethods {

	public static void main(String ... args) {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios1 = Arrays.asList(user1, user2, user3);

 		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");

 		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());

 		// Imprimir o "mostraMensagem" antes de cada um dos nomes dos usu�rios
		usuarios1.forEach(mostraMensagem.andThen(imprimeNome));

 		List<Usuario> usuarios2 = new ArrayList<Usuario>();
		usuarios2.addAll(usuarios1);

 		// Remover todos os usu�rio com mais de 160 pontos - Vers�o 1
		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};

 		usuarios2.removeIf(predicado);

 		usuarios2.forEach(u -> System.out.println(u));

 		// Remover todos os usu�rio com mais de 160 pontos - Vers�o 2
		usuarios2.removeIf(u -> u.getPontos() > 160);

 		usuarios2.forEach(u -> System.out.println(u));
	}
}
