package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoRepository;

//Usar a classe informada p/ executar os testes, SpringRunner inicia o contexto
@RunWith(SpringRunner.class)
//Configura Data Jpa e configura um banco embutido em memoria
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContatosRepositoryIntegrationTest {

	@Autowired
	private ContatoRepository repository;

//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();

	private Contato contato;

	@Before
	public void start() {
		contato = new Contato("Chefe", "0y", "9xxxxx9");
	}
	
	@After
	public void after() {
		repository.deleteAll();
	}
	
	@Test
	public void saveComDDDNullDeveLancarExcpetion() {
//		expectedException.expect(ConstraintViolationException.class);
//		expectedException.expectMessage("O DDD deve ser preenchido");
		
		contato.setDdd(null);
		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			repository.save(contato);
		});
		String expectedMessage = "O DDD deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	
	}
	
	@Test
	public void saveComTelefoneNullDeveLancarExcpetion() {	
		contato.setTelefone(null);
		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			repository.save(contato);
		});
		String expectedMessage = "O Telefone deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void saveComNomeNullDeveLancarExcpetion() {
		contato.setNome(null);
		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			repository.save(contato);
		});
		String expectedMessage = "O Nome deve ser preenchido";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void saveDeveSalvarContato() {
		repository.save(contato);
		List<Contato> contatos = repository.findAll();
		assertEquals(1, contatos.size());
		repository.deleteAll();
	}
	
	@Test
	public void deleteByIdDeveRemoverContato() {
		repository.save(contato);
		List<Contato> contatos = repository.findAll();
		assertEquals(1, contatos.size());
		
		repository.deleteById(contato.getId());
		List<Contato> contatos2 = repository.findAll();
		assertEquals(0, contatos2.size());
	}
}
