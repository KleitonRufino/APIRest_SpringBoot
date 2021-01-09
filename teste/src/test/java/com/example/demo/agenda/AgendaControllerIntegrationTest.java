package com.example.demo.agenda;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;

import com.example.demo.contatos.Contato;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class AgendaControllerIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private MockMvc mockMvc;

	private Contato contato;

	@Before
	public void start() throws Exception {
		contato = new Contato("Chefe", "0y", "9xxxxxxx9");
		testEntityManager.persist(contato);
	}

	@After
	public void end() {
		testEntityManager.getEntityManager().createQuery("DELETE FROM Contato").executeUpdate();
	}

	@Test
	public void checaStatus() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		StatusResultMatchers status = MockMvcResultMatchers.status();

		resultActions.andExpect(status.isOk());

		resultActions.andExpect(status.is(200));

		resultActions.andExpect(status.is(Matchers.is(200)));

	}

	@Test
	public void checaView() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		ViewResultMatchers view = MockMvcResultMatchers.view();

		resultActions.andExpect(view.name("agenda"));

		resultActions.andExpect(view.name(Matchers.is("agenda")));
	}

	@Test
	public void checaModel() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		ModelResultMatchers model = MockMvcResultMatchers.model();

		resultActions.andExpect(model.attributeExists("contatos"));

		resultActions.andExpect(model.attribute("contatos", Matchers.hasSize(1)));

		resultActions.andExpect(model.attribute("contatos",
				Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("id", Matchers.is(contato.getId())),
						Matchers.hasProperty("nome", Matchers.is(contato.getNome())),
						Matchers.hasProperty("ddd", Matchers.is(contato.getDdd())),
						Matchers.hasProperty("telefone", Matchers.is(contato.getTelefone()))))));
	}

}
