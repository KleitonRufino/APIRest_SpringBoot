//***********************************************************************
//
//	Cap�tulo 11 - Um Modelo de Pagamentos com Java 8
//
//***********************************************************************

package java8;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExemploPratico {

	public static void main(String ... args) {
		
		// Criando Consumidores
		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");
		
		// Criando produtos
		Product bach = 
				new Product("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = 
				new Product("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = 
				new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = 
				new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = 
				new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = 
				new Product("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));
		
		// Cirando as datas
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
		LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
		
		// Criando os pagamentos
		Payment payment1 = new Payment(Arrays.asList(bach, poderosas), today, paulo);
		Payment payment2 = new Payment(Arrays.asList(bach, bandeira, amelie), yesterday, rodrigo); 
		Payment payment3 = new Payment(Arrays.asList(beauty, vingadores, bach), today, adriano); 
		Payment payment4 = new Payment(Arrays.asList(bach, poderosas, amelie), lastMonth, guilherme); 
		Payment payment5 = new Payment(Arrays.asList(beauty, amelie), yesterday, paulo);
		
		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);
		
		// Ordena os pagamentos por data
		payments.stream()
				.sorted(Comparator.comparing(Payment::getDate))
				.forEach(System.out::println);
		
		// Soma de todos os produtos de payment1 - vers�o 1
		payment1.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal::add)
				.ifPresent(System.out::println);
		
		// Soma de todos os produtos de payment1 - vers�o 2
		BigDecimal total1 = payment1.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// Soma de todos os produtos de todos os pagamentos - vers�o 1
		Stream<BigDecimal> priceStream = payments.stream()
				.map(p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		// Soma de todos os produtos de todos os pagamentos - vers�o 2
		BigDecimal total2 = payments.stream()
				.map(p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// Cria um Stream<BigDecimal> com todos os valores de produtos - vers�o 1
		Stream<BigDecimal> priceOfEachproduct = payments.stream()
				.flatMap(p -> p.getProducts().stream()
						.map(Product::getPrice));
		
		// Cria um Stream<BigDecimal> com todos os valores de produtos - vers�o 2
		Function<Payment, Stream<BigDecimal>> mapper = p -> p.getProducts().stream()
				.map(Product::getPrice);
		
		// Soma de todos os produtos de todos os pagamentos - vers�o 3
		BigDecimal totalFlat = payments.stream()
				.flatMap(p -> p.getProducts().stream()
						.map(Product::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// Imprimi os produtos mais vendidos
		Map<Product, Long> topProducts = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(topProducts);
		
		// Imprimi o produto mais vendido
		topProducts.entrySet().stream()
				.max(Comparator.comparing(Map.Entry::getValue))
				.ifPresent(System.out::println);
		
		// Imprimir a soma do valor por produto
		Map<Product, BigDecimal> totalValuePerproduct = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));
		
		totalValuePerproduct.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(System.out::println);
		
		// Imprimi a lista de produtos de cada cliente
		Map<Customer, List<List<Product>>> customerToproductList = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer, 
						Collectors.mapping(Payment::getProducts, Collectors.toList())));
		
		customerToproductList.entrySet().stream()
				.sorted(Comparator.comparing(e -> e.getKey().getName()))
				.forEach(System.out::println);
		
		// Imprimi a lista de produtos de cada cliente em uma unica lista
		Map<Customer, List<Product>> customerToproduct2Steps = customerToproductList.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey,
						e -> e.getValue().stream()
								.flatMap(List::stream)
								.collect(Collectors.toList())));
		
		customerToproduct2Steps.entrySet().stream()
				.sorted(Comparator.comparing(e -> e.getKey().getName()))
				.forEach(System.out::println);
		
		// Retorna a lista de produtos de cada cliente em uma unica lista - versao 1
		Map<Customer, List<Product>> customerToproduct1Steps = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer, 
						Collectors.mapping(Payment::getProducts, Collectors.toList())))
				.entrySet().stream()
						.collect(Collectors.toMap(Map.Entry::getKey, 
								e -> e.getValue().stream()
								.flatMap(List::stream)
								.collect(Collectors.toList())));
		
		// Retorna a lista de produtos de cada cliente em uma unica lista - versao 2
		Map<Customer, List<Product>> customerToProduct = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer, 
						Collectors.reducing(Collections.emptyList(), 
								Payment::getProducts, 
								(l1, l2) -> { List<Product> l = new ArrayList<>();
											  l.addAll(l1);
											  l.addAll(l2);
											  return l; } )));
		
		// Imprimi o valor total por usuario
		Function<Payment, BigDecimal> paymentToTotal = p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Map<Customer, BigDecimal> totalvaluePerCustomer = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
						Collectors.reducing(BigDecimal.ZERO, paymentToTotal, BigDecimal::add)));
		
		totalvaluePerCustomer.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(System.out::println);
		
		// Imprimi os pagamentos por data
		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));
		
		paymentsPerMonth.entrySet().stream()
				.forEach(System.out::println);
		
		// Imprimi o faturamento por m�s
		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate()),
						Collectors.reducing(BigDecimal.ZERO,
								p -> p.getProducts().stream()
										.map(Product::getPrice)
										.reduce(BigDecimal.ZERO, 
												BigDecimal::add),
										BigDecimal::add)));
		
		paymentsValuePerMonth.entrySet().stream()
				.forEach(System.out::println);
		
		BigDecimal monthlyFee = new BigDecimal("99.90");
		
		// Criando subscriptions
		Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), paulo);
		Subscription s2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), rodrigo);
		Subscription s3 = new Subscription(monthlyFee, yesterday.minusMonths(5), today.minusMonths(2), adriano);
		
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		
		// Calcula o n�mero de meses pagos - vers�o 1
		long meses1 = ChronoUnit.MONTHS.between(s1.getBegin(), LocalDateTime.now());
		
		// Calcula o n�mero de meses pagos - vers�o 2
		long meses2 = ChronoUnit.MONTHS.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()));
		
		// Calcula o valor gerado por uma assinatura
		BigDecimal total3 = s1.getMonthlyFee().multiply(new BigDecimal(ChronoUnit.MONTHS
				.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()))));
		
		// calcula o valor total pago
		BigDecimal totalPaid = subscriptions.stream()
				.map(Subscription::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
