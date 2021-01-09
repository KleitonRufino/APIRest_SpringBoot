package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.agenda.AgendaController;
import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoException;
import com.example.demo.contatos.ContatoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgendaControllerIntegrationTest {

	@MockBean
	private ContatoRepository repository;

	@Autowired
	private AgendaController controller;

	private String nome = "Chefe";
	private String ddd = "0y";
	private String telefone = "9xxxxxx9";

	@Test
	public void saveComDDDNullDeveLancarExcpetion() throws ContatoException {
		Mockito.when(repository.save((Contato) Mockito.any()))
				.thenThrow(new ConstraintViolationException("O DDD deve ser preenchido", null));
		Exception exception = assertThrows(ContatoException.class, () -> {
			controller.inserirRegistro(nome, null, telefone);
		});
		String expectedMessage = "O DDD deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveComTelefoneNullDeveLancarExcpetion() {
		Mockito.when(repository.save((Contato) Mockito.any()))
				.thenThrow(new ConstraintViolationException("O Telefone deve ser preenchido", null));
		Exception exception = assertThrows(ContatoException.class, () -> {
			controller.inserirRegistro(nome, ddd, null);
		});
		String expectedMessage = "O Telefone deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveComNomeNullDeveLancarExcpetion() {
		Mockito.when(repository.save((Contato) Mockito.any()))
				.thenThrow(new ConstraintViolationException("O Nome deve ser preenchido", null));
		Exception exception = assertThrows(ContatoException.class, () -> {
			controller.inserirRegistro(null, ddd, telefone);
		});
		String expectedMessage = "O Nome deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveDeveSalvarContato() throws ContatoException {
		controller.inserirRegistro(nome, ddd, telefone);
		Mockito.verify(repository, Mockito.times(1)).save(new Contato(nome, ddd, telefone));
	}
	
	@Test
	public void deleteByIdDeveRemoverContato() {
		controller.removerRegistro(1L);
		Mockito.verify(repository, Mockito.times(1)).deleteById(1L);;
	}
}
