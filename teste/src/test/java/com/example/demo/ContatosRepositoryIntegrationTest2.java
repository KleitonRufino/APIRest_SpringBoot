package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoRepository;

//Usar a classe informada p/ executar os testes, SpringRunner inicia o contexto
@RunWith(SpringRunner.class)
//Configura Data Jpa e configura um banco embutido em memoria
@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
public class ContatosRepositoryIntegrationTest2 {

	@Autowired
	private ContatoRepository repository;


	
	@Before
	public void start() {
		Contato contato1 = new Contato("Chefe", "0y", "9xxxxx9");
		Contato contato2 = new Contato("Novo Chefe", "0y", "8xxxxx8");
		Contato contato3 = new Contato("Chefe mais antigo", "0y", "7xxxxx7");
		Contato contato4 = new Contato("Amigo", "0z", "6xxxxx6");
		List<Contato> list = new ArrayList<Contato>();
		list.add(contato1);
		list.add(contato2);
		list.add(contato3);
		list.add(contato4);
		repository.saveAll(list);
	}
	
	@After
	public void after() {
		repository.deleteAll();
	}
	
	@Test
	public void findAllByNomeRetornaContato() {
		Contato contato = repository.findFirstByNome("Chefe");
		assertTrue(contato.getNome().equals("Chefe"));
	}
	
	@Test
	public void findAllByNomeIgnoreCaseRetornaTresContatos() {
		List<Contato> contatos = repository.findAllByNomeIgnoreCaseContaining("chefe");
		assertEquals(3, contatos.size());
	}
	
	@Test
	public void buscaTodosContatosComOrdenacaoRetornaListaOrdenadaDeFormaAscendente() {
		List<Contato> contatos = repository.buscaTodosContatosComOrdenacao(Sort.by(Sort.Direction.ASC, "nome"));
		assertTrue(contatos.get(0).getNome().equals("Amigo"));
	}
	
	@Test
	@Transactional
	public void buscaTodosContatosRetornaStreamOrdenada() {
		try(Stream<Contato> contatos = repository.buscaTodosContatos()){
			assertTrue(contatos.findFirst().get().getNome().equals("Amigo"));
		}
	}
}
