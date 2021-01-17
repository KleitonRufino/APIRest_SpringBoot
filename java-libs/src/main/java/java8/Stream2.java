//***********************************************************************
//
//	Cap�tulo 8 - Mais Opera��es com Streams
//
//***********************************************************************

package java8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream2 {

	public static void main(String ... args) throws IOException {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
 		
 		// Filtra os usu�rios com mais de 100 pontos e orden�-los
 		List<Usuario> filtradosOrdenados = usuarios.stream()
 				.filter(u -> u.getPontos() > 100)
 				.sorted(Comparator.comparing(Usuario::getNome))
 				.collect(Collectors.toList());
 		
 		// Retorna aleat�riamente qualquer usu�rio com mais de 100 pontos
 		Optional<Usuario> usuarioOptional = usuarios.stream()
 				.filter(u -> u.getPontos() > 100)
 				.findAny();
 		
 		// O stream executar� uma tarefa sempre que processar um elemento (com findAny)
 		usuarios.stream().filter(u -> u.getPontos() > 100)
 				.peek(System.out::println)
 				.findAny();
 		
 		// O stream executar� uma tarefa sempre que processar um elemento (sem findAny)
 		usuarios.stream().filter(u -> u.getPontos() > 100)
 				.peek(System.out::println);
 		
 		// O stream executar� uma tarefa sempre que processar um elemento (com sorted)
 		usuarios.stream().sorted(Comparator.comparing(Usuario::getNome))
 				.peek(System.out::println)
 				.findAny();
 		
 		// Retorna a pontua��o m�dia
 		double pontuacaoMedia = usuarios.stream()
 				.mapToInt(Usuario::getPontos)
 				.average()
 				.getAsDouble();
 		
 		// Retorna o usu�rio com a pontua��o max�ma
 		Usuario pontuacaoMaxima = usuarios.stream()
 				.max(Comparator.comparing(Usuario::getPontos))
 				.get();
 		
 		// Retorna a soma da pontua��o de todos os usu�rios - vers�o 1
 		int pontuacaoTotal1 = usuarios.stream()
 				.mapToInt(Usuario::getPontos)
 				.sum();
 		
 		// Retorna a soma da pontua��o de todos os usu�rios - vers�o 2
 		int pontuacaoTotal2 = usuarios.stream()
 				.mapToInt(Usuario::getPontos)
 				.reduce(0, (a, b) -> a + b);
 		
 		// Retorna a soma da pontua��o de todos os usu�rios - vers�o 3
 		int pontuacaoTotal3 = usuarios.stream()
 				.mapToInt(Usuario::getPontos)
 				.reduce(0, Integer::sum);
 		
 		// Retorna a soma da pontua��o de todos os usu�rios - vers�o 4
 		int pontuacaoTotal4 = usuarios.stream()
 				.reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);
 		
 		// Retorna a multiplica��o da pontua��o de todos os usu�rios
 		int multiplicacao = usuarios.stream()
 				.mapToInt(Usuario::getPontos)
 				.reduce(1, (a, b) -> a * b);
 		
 		// Percorrendo usu�rios com um iterator
 		usuarios.stream().iterator()
 				.forEachRemaining(System.out::println);
 		
 		// Verifica se algum usu�rio � moderador
 		boolean hasModerador = usuarios.stream()
 				.anyMatch(Usuario::isModerador);
 		
 		// Limita a cria��o de n�meros randomicos a 100
 		Random random = new Random(0);
 		
 		List<Integer> list = IntStream
 				.generate(() -> random.nextInt())
 				.limit(100)
 				.boxed()
 				.collect(Collectors.toList());
 		
 		// Imprimi os 10 primeiros n�meros Fibonacci
 		IntStream.generate(new Fibonacci())
 				.limit(10)
 				.forEach(System.out::println);
 		
 		// Imprimi o primeiro elemento maior que 100 da sequencia de Fibonacci
 		int maiorQue100 = IntStream
 				.generate(new Fibonacci())
 				.filter(f -> f > 100)
 				.findFirst()
 				.getAsInt();
 		
 		System.out.println(maiorQue100);
 		
 		// Imprimi os 10 primeiros n�meros
 		IntStream.iterate(0, x -> x + 1)
 				.limit(10)
 				.forEach(System.out::println);
 		
 		// Lista todos os arquivos de um diret�rio
 		Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
 				.forEach(System.out::println);
 		
 		// Lista todos os arquivos '.java' de um diret�rio
 		Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
 				.filter(p -> p.toString().endsWith(".java"))
 				.forEach(System.out::println);
 		
 		// Mostra todo os conte�do dos '.java' de um diret�rio - vers�o 1
 		Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
			.filter(p -> p.toString().endsWith(".java"))
			.map(p -> lines(p))
			.forEach(System.out::println);
	
 		// Imprimi todo os conte�do dos '.java' de um diret�rio - vers�o 2
 		Stream<Stream<String>> strings1 = 
 				Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
 						.filter(p -> p.toString().endsWith(".java"))
 						.map(p -> lines(p));
 		
 		// Imprimi todo os conte�do dos '.java' de um diret�rio - vers�o 3
 		Stream<String> string2 = 
 				Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
 						.filter(p -> p.toString().endsWith(".java"))
 						.flatMap(p -> lines(p));
 		
 		// Retorna todos os caracteres de todos os arquivos '.java'
 		IntStream chars = Files.list(Paths.get("./src/br/com/hosseinramon/java8"))
 				.filter(p -> p.toString().endsWith(".java"))
 				.flatMap(p -> lines(p))
 				.flatMapToInt((String s) -> s.chars());
 		
 		Grupo englishSpeakers = new Grupo();
 		englishSpeakers.add(user1);
 		englishSpeakers.add(user2);
 		
 		Grupo spanishSpeaker = new Grupo();
 		spanishSpeaker.add(user2);
 		spanishSpeaker.add(user3);
 		
 		List<Grupo> groups = Arrays.asList(englishSpeakers, spanishSpeaker);
 		
 		// Imprimi todos os usu�rios de ambos os grupos sem nenhuma repeti��o
 		groups.stream().flatMap(g -> g.getUsuario().stream())
 				.distinct()
 				.forEach(System.out::println);
	}
	
	// Classe an�nima utilizada para validar o 'lines'
	static Stream<String> lines(Path p) {
		
		try {
			return Files.lines(p);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
