package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Pessoa;
import com.example.demo.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private PessoaRepository repository;

	@Test
	public void whenFindById_thenReturnEmployee() {
		// given

		Pessoa pessoa = new Pessoa();
		pessoa.setEmail("email");
		pessoa.setNome("nome");
		pessoa.setTelefone("888888888888888888");

		entityManager.persist(pessoa);
		entityManager.flush();

		// when
		Pessoa found = repository.findById(pessoa.getId());

		// then
		assertEquals(found.getId(), pessoa.getId());
	}
}
