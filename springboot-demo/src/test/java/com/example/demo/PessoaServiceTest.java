package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Pessoa;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.PessoaService;
import com.example.demo.service.PessoaServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaServiceTest {

	@TestConfiguration
	static class PessoaServiceTestContextConfiguration {
		@Bean
		public PessoaService pessoaService() {
			return new PessoaServiceImpl();
		}
	}

	@Autowired
	private PessoaService service;
	@MockBean
	private PessoaRepository repository;

	@Before
	public void setUp() {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		pessoa.setEmail("email");
		pessoa.setNome("nome");
		pessoa.setTelefone("888888888888888888");

		Mockito.when(repository.findById(1)).thenReturn(pessoa);
	}

	@Test
	public void whenId_thenEmployeeShouldBeFound() {
		int id = 1;
		Pessoa found = service.encontrarPorId(id);

		assertEquals(found.getId(), id);
	}
}
