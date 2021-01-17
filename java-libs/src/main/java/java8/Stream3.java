//***********************************************************************
//
//	Cap�tulo 9 - Mapeando, Particionando, Agrupando e Paralelizando
//
//***********************************************************************

package java8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Stream3 {

	public static void main(String ... args) throws IOException {
		
 		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
 		
		// Retorna a quantidade de linhas de cada arquivo em uma LongStream
		LongStream lines1 = Files.list(Paths.get("./src/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.mapToLong(p -> lines(p).count());
		
		// Retorna a quantidade de linhas de cada arquivo em uma List<Long>
		List<Long> lines2 = Files.list(Paths.get("./src/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.map(p -> lines(p).count())
				.collect(Collectors.toList());
		
		// Retorna e imprimi a quantidade de linhas de cada arquivo em um Map<Path, Long> - vers�o 1
		Map<Path, Long> linesPerFile1 = new HashMap<>();
		
		Files.list(Paths.get("./src/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.forEach(p -> linesPerFile1.put(p, lines(p).count()));
		
		System.out.println(linesPerFile1);
		
		// Retorna e imprimi a quantidade de linhas de cada arquivo em um Map<Path, Long> - vers�o 2
		Map<Path, Long> linesPerFile2 = Files.list(Paths.get("./src/sjava8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(p -> p, p -> lines(p).count()));
		
		System.out.println(linesPerFile2);
		
		// Retorna um mapa de cada arquivo para toda a lista de linhas contidas nos arquivos
		Map<Path, List<String>> content = Files.list(Paths.get("./src/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						Function.identity(), 
						p -> lines(p).collect(Collectors.toList())));

		// Mapear todos os usu�rios utilizando seu nome como chave
		Map<String, Usuario> nameToUser = usuarios.stream()
				.collect(Collectors.toMap(
						Usuario::getNome, 
						Function.identity()));
		
		// Imprimi um mapa de pontua��o por usu�rios com aquela pontua��o - vers�o 1
		Map<Integer, List<Usuario>> pontuacao1 = new HashMap<>();
		
		for(Usuario u : usuarios) {
			if(!pontuacao1.containsKey(u.getPontos())) {
				pontuacao1.put(u.getPontos(), new ArrayList<Usuario>());
			}
			
			pontuacao1.get(u.getPontos()).add(u);
		}
		
		System.out.println(pontuacao1);
		
		// Imprimi um mapa de pontua��o por usu�rios com aquela pontua��o - vers�o 2
		Map<Integer, List<Usuario>> pontuacao2 = new HashMap<>();
		
		for(Usuario u : usuarios) {
			pontuacao2.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
		}
		
		System.out.println(pontuacao2);
		
		// Imprimi um mapa de pontua��o por usu�rios com aquela pontua��o - vers�o 3
		Map<Integer, List<Usuario>> pontuacao3 = usuarios.stream()
				.collect(Collectors.groupingBy(Usuario::getPontos));
		
		System.out.println(pontuacao3);
		
		// Imprimi todos os usu�rios particionados entre moderadores e n�o moderadores
		Map<Boolean, List<Usuario>> moderadores = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador));
		
		System.out.println(moderadores);
		
		// Imprimi o nome de usu�rios particionados entre moderadores e n�o moderadores
		Map<Boolean, List<String>> nomesPorTipo = usuarios.stream()
				.collect(Collectors.partitioningBy(
						Usuario::isModerador,
						Collectors.mapping(
								Usuario::getNome, 
								Collectors.toList())));
		
		System.out.println(nomesPorTipo);
		
		// Imprimi a soma dos pontos particionados entre moderadores e n�o moderadores
		Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
				.collect(Collectors.partitioningBy(
						Usuario::isModerador,
						Collectors.summingInt(Usuario::getPontos)));
		
		System.out.println(pontuacaoPorTipo);
		
		// Retorna usu�rios com mais de 100 pontos e ordenados - vers�o 1
		List<Usuario> filtradosOrdenados1 = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		// Retorna usu�rios com mais de 100 pontos e ordenados - vers�o 2
		List<Usuario> filtradosOrdenados2 = usuarios.parallelStream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		// Soma os n�meros pares de 0 � 1.000.000.000
		long sum = LongStream.range(0, 1_000_000_000)
				.parallel()
				.filter(x -> (x % 2) == 0)
				.sum();
		
		System.out.println(sum);
	}
	 
	// Classe an�nima utilizada para validar o 'lines'
	static Stream<String> lines(Path p) {
		
		try {
			return Files.lines(p, Charset.defaultCharset());
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
