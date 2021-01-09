package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.management.Query;
import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.contatos.Contato;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class AgendaControllerIntegrationTest2 {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MockMvc mockMvc;

	private Contato contato;

	@Before
	public void start() {
		contato = new Contato("Chefe", "0y", "9xxxxxxxx9");
		entityManager.persist(contato);
	}

	@After
	public void end() {
		entityManager.getEntityManager().createQuery("DELETE FROM Contato").executeUpdate();
	}

	@Test
	public void deveMostrarTodosContatos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/agenda"));
	}

	@Test
	public void status() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
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

	@Test
	public void checaView_2() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		ModelAndView modelAndView = resultActions.andReturn().getModelAndView();

		Assert.assertEquals("agenda", modelAndView.getViewName());
	}

	@Test
	public void checaModel_2() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		ModelAndView modelAndView = resultActions.andReturn().getModelAndView();
		List<Contato> contatos = (List<Contato>) modelAndView.getModel().get("contatos");

		assertEquals(1, contatos.size());
		assertTrue(contatos.contains(contato));
	}

	@Test
	public void deveMostrarTodosOsContatos() throws Exception {
		entityManager.persist(contato);
		mockMvc.perform(MockMvcRequestBuilders.get("/agenda/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("agenda"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("contatos"))
				.andExpect(MockMvcResultMatchers.model().attribute("contatos", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.model().attribute("contatos",
						Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("id", Matchers.is(contato.getId())),
								Matchers.hasProperty("nome", Matchers.is(contato.getNome())),
								Matchers.hasProperty("ddd", Matchers.is(contato.getDdd())),
								Matchers.hasProperty("telefone", Matchers.is(contato.getTelefone()))))))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deveMostrarUmContato() throws Exception {
		Long id = (Long) entityManager.persistAndGetId(contato);
		mockMvc.perform(MockMvcRequestBuilders.get("/agenda/contato/{id}", id))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("agenda/contato"))
		.andExpect(MockMvcResultMatchers.model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(MockMvcResultMatchers.model().attribute("contato", contato))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void removerDeveExcluirContato() throws Exception {
		Long id = (Long) entityManager.persistAndGetId(contato);
		mockMvc.perform(MockMvcRequestBuilders.get("/agenda/remover/{id}", id))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:agenda/"))
		.andExpect(MockMvcResultMatchers.flash().attribute("successMessage", "Contato removido com sucesso"))
		.andDo(MockMvcResultHandlers.print());
		
		javax.persistence.Query query = entityManager.getEntityManager().createQuery("SELECT c FROM Contato c");
		List<Contato> resultList = query.getResultList();
		assertEquals(0, resultList.size());
	}
	
	@Test
	public void inserirComDDDNullDeveRetornarUmErro() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda/inserir")
				.param("telefone", "9xxxxxx9").
				param("nome", "Chefe"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("agenda/inserir"))
		.andExpect(MockMvcResultMatchers.model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("contato", "ddd"))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("contato", "ddd", "NotEmpty"))
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void inserirComTelefoneNullDeveRetornarUmErro() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda/inserir")
				.param("ddd", "0y").
				param("nome", "Chefe"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("agenda/inserir"))
		.andExpect(MockMvcResultMatchers.model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("contato", "telefone"))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("contato", "telefone", "NotEmpty"))
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void inserirDeveSalvarContato() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda/inserir")
				.param("ddd", "0y")
				.param("telefone", "9xxxxxxxx9")
				.param("nome", "Chefe"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("agenda/inserir"))
		.andExpect(MockMvcResultMatchers.model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(MockMvcResultMatchers.flash().attribute("successMessage", "Contato removido com sucesso"))
		.andDo(MockMvcResultHandlers.print());
		
		javax.persistence.Query query = entityManager.getEntityManager().createQuery("SELECT c FROM Contato c");
		List<Contato> resultList = query.getResultList();
		assertEquals(0, resultList.size());
	}
}
