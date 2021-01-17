//***********************************************************************
//
//	Cap�tulo 5 - Ordenando no Java 8
//
//***********************************************************************

package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Ordenacao1 {

	public static void main(String ... args) {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 1
		Comparator<Usuario> comparator1 = new Comparator<Usuario>() {
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};

 		Collections.sort(usuarios, comparator1);

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 2
		Comparator<Usuario> comparator2 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());

 		Collections.sort(usuarios, comparator2);

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 3
		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 4
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 5
		Comparator<Usuario> comparator3 = Comparator.comparing(u -> u.getNome());

 		usuarios.sort(comparator3);

 		// Ordenar os usu�rio de forma alfab�tica - vers�o 6
		usuarios.sort(Comparator.comparing(u -> u.getNome()));

 		// Ordenar os usu�rio de forma alfab�tica << Ordem natural >>
		List<String> palavras = Arrays.asList("Casa do Codigo", "Alura", "Caelum");

 		palavras.sort(Comparator.naturalOrder());

 		// Ordenar os usu�rio de forma alfab�tica << Ordem reversa >>
		palavras.sort(Comparator.reverseOrder());

 		// Ordenar os usu�rio por pontos - vers�o 1
		Function<Usuario, Integer> extraiPontos1 = u -> u.getPontos();

 		Comparator<Usuario> comparator4 = Comparator.comparing(extraiPontos1);

 		usuarios.sort(comparator4);

 		// Ordenar os usu�rio por pontos - vers�o 2
		usuarios.sort(Comparator.comparing(u -> u.getPontos()));

 		// Ordenar os usu�rio por pontos - vers�o 3
		ToIntFunction<Usuario> extraiPontos2 = u -> u.getPontos();

 		Comparator<Usuario> comparator5 = Comparator.comparingInt(extraiPontos2);

 		usuarios.sort(comparator5);

 		// Ordenar os usu�rio por pontos - vers�o 4
		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
	}
}
