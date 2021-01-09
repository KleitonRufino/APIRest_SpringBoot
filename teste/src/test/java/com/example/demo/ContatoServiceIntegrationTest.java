package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoException;
import com.example.demo.contatos.ContatoRepository;
import com.example.demo.contatos.ContatoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContatoServiceIntegrationTest {

	@Autowired
	private ContatoService service;

	@Autowired
	private ContatoRepository repository;

	private Contato contato;

	@Before
	public void start() {
		contato = new Contato("Chefe", "0y", "9xxxxxxxxx9");
	}

	@Test
	public void saveComDDDNullDeveLancarExcpetion() {

		contato.setDdd(null);
		Exception exception = assertThrows(ContatoException.class, () -> {
			service.inserir(contato);
		});
		String expectedMessage = "O DDD deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void saveComTelefoneNullDeveLancarExcpetion() {
		contato.setTelefone(null);
		Exception exception = assertThrows(ContatoException.class, () -> {
			service.inserir(contato);
		});
		String expectedMessage = "O Telefone deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveComNomeNullDeveLancarExcpetion() {
		contato.setNome(null);
		Exception exception = assertThrows(ContatoException.class, () -> {
			service.inserir(contato);
		});
		String expectedMessage = "O Nome deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveDeveSalvarContato() throws ContatoException {
		service.inserir(contato);
		List<Contato> contatos = repository.findAll();
		assertEquals(1, contatos.size());
		repository.deleteAll();
	}

	@Test
	public void deleteByIdDeveRemoverContato() {
		repository.save(contato);
		List<Contato> contatos = repository.findAll();
		assertEquals(1, contatos.size());

		service.remover(contato.getId());
		List<Contato> contatos2 = repository.findAll();
		assertEquals(0, contatos2.size());
	}
}
