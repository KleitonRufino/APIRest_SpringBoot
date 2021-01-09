package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AgendaControllerIntegrationRestTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ContatoRepository contatoRepository;

	private Contato contato;

	private String ddd = "0y";

	private String telefone = "9xxxxxxx9";

	@Before
	public void start() {
		contato = new Contato("Chefe", "0y", "9xxxxxxx9");
		contatoRepository.save(contato);
	}

	@After
	public void end() {
		contatoRepository.deleteAll();
	}

	@Test
	public void deveMostrarTodosContatos() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/agenda2/", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	public void deveMostrarTodosContatosUsandoString() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/agenda2/", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json")));
		
		String result = "[{\"id\":" + contato.getId() +",\"ddd\":\"0y\",\"telefone\":\"9xxxxxxx9\",\"nome\":\"Chefe\",\"estado\":null}]";
		assertEquals(result, resposta.getBody());
	}
	
	@Test
	public void deveMostrarTodosContatosUsandoList() {
		ParameterizedTypeReference<List<Contato>> parameterizedTypeReference = new ParameterizedTypeReference<List<Contato>>() {
		};
		ResponseEntity<List<Contato>> resposta = testRestTemplate.exchange("/agenda2/", HttpMethod.GET, null, parameterizedTypeReference);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json")));
		assertEquals(1, resposta.getBody().size());
		assertEquals(contato, resposta.getBody().get(0));
	}
	
	@Test
	public void deveMostrarUmContato() {
		ResponseEntity<Contato> resposta = testRestTemplate.exchange("/agenda2/contato/{id}", HttpMethod.GET, null, Contato.class, contato.getId());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json")));
		
		assertEquals(contato, resposta.getBody());
	}
	
	@Test
	public void buscaUmContratoDeveRetornarNaoEncontrado() {
		ResponseEntity<Contato> resposta = testRestTemplate.exchange("/agenda2/contato/{id}", HttpMethod.GET, null, Contato.class, 100);
		
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		assertNull(resposta.getBody());
	}
	
	
	@Test
	public void deveMostrarUmContatoComGetForEntity() {
		ResponseEntity<Contato> resposta = testRestTemplate.getForEntity("/agenda2/contato/{id}", Contato.class, contato.getId());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json")));
		
		assertEquals(contato, resposta.getBody());
	}
	
	@Test
	public void deveMostrarUmContatoComGetForObject() {
		Contato resposta = testRestTemplate.getForObject("/agenda2/contato/{id}", Contato.class, contato.getId());
		assertEquals(contato, resposta);
	}
	
	@Test
	public void buscaUmContratoDeveRetornarNaoEncontradoComGetForEntity() {
		ResponseEntity<Contato> resposta = testRestTemplate.getForEntity("/agenda2/contato/{id}", Contato.class, 100);
		
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		assertNull(resposta.getBody());
	}
	
	@Test
	public void salvarContatoDeveRetornarMensagemDeErro() {
		Contato contato = new Contato("Chefe", null, null);
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<String> resposta = testRestTemplate.exchange("/agenda2/inserir", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<String>() {
		});
		
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("O DDD deve ser preenchido"));
		assertTrue(resposta.getBody().contains("O Telefone deve ser preenchido"));
	}

	@Test
	public void salvarContatoDeveSalvarContato() {
		HttpEntity<Contato> httpEntity = new HttpEntity<Contato>(contato);
		ResponseEntity<Contato> resposta = testRestTemplate.exchange("/agenda2/inserir", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Contato>() {
		});
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		
		Contato resultado = resposta.getBody();
		assertNotNull(resultado.getId());
		assertEquals(contato.getNome(), resultado.getNome());
		assertEquals(contato.getTelefone(), resultado.getTelefone());
		assertEquals(contato.getDdd(), resultado.getDdd());
	}
	
	@Test
	public void salvarContatoDeveSalvarContatoComPostForEntity() {
		HttpEntity<Contato> httpEntity = new HttpEntity<Contato>(contato);
		ResponseEntity<Contato> resposta = testRestTemplate.postForEntity("/agenda2/inserir", httpEntity, Contato.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		
		Contato resultado = resposta.getBody();
		assertNotNull(resultado.getId());
		assertEquals(contato.getNome(), resultado.getNome());
		assertEquals(contato.getTelefone(), resultado.getTelefone());
		assertEquals(contato.getDdd(), resultado.getDdd());
	}
	
	@Test
	public void salvarContatoDeveSalvarContatoComPostForObject() {
		HttpEntity<Contato> httpEntity = new HttpEntity<Contato>(contato);
		Contato resultado = testRestTemplate.postForObject("/agenda2/inserir", httpEntity, Contato.class);
		
		assertNotNull(resultado.getId());
		assertEquals(contato.getNome(), resultado.getNome());
		assertEquals(contato.getTelefone(), resultado.getTelefone());
		assertEquals(contato.getDdd(), resultado.getDdd());
	}
	
	@Test
	public void alterarDeveRetornarMensagemDeErro() {
		contato.setDdd(null);
		contato.setTelefone(null);
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<String> resposta = 
				testRestTemplate.exchange("/agenda2/alterar/{id}",HttpMethod.PUT
						,httpEntity, new ParameterizedTypeReference<String>() {},contato.getId());
		
		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("O DDD deve ser preenchido"));
		assertTrue(resposta.getBody().contains("O Telefone deve ser preenchido"));
	}

	@Test
	public void alterarDeveAlterarContato() {
		contato.setNome("Novo Chefe");
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda2/alterar/{id}",HttpMethod.PUT,httpEntity
						, Contato.class,contato.getId());
		
		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		Contato resultado = resposta.getBody(); 
		assertEquals(contato.getId(), resultado.getId());
		assertEquals(ddd, resultado.getDdd());
		assertEquals(telefone, resultado.getTelefone());
		assertEquals("Novo Chefe", resultado.getNome());
	}

	@Test
	public void alterarDeveAlterarContatoComPut() {
		contato.setNome("Novo Chefe");
		testRestTemplate.put("/agenda2/alterar/{id}",contato,contato.getId());

		Contato resultado = contatoRepository.findById(contato.getId()).get();
		assertEquals(ddd, resultado.getDdd());
		assertEquals(telefone, resultado.getTelefone());
		assertEquals("Novo Chefe", resultado.getNome());
	}

	@Test
	public void removerDeveExcluirContato() {
		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda2/remover/{id}",HttpMethod.DELETE,null
						, Contato.class,contato.getId());
		
		assertEquals(HttpStatus.NO_CONTENT,resposta.getStatusCode());
		assertNull(resposta.getBody());
	}

	@Test
	public void removerDeveExcluirContatoComDelete() {
		testRestTemplate.delete("/agenda2/remover/"+contato.getId());
		
		Optional<Contato> resultado = contatoRepository.findById(contato.getId());
		assertEquals(Optional.empty(), resultado);
	}
}
	
