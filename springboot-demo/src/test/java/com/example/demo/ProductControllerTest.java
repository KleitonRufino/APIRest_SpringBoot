package com.example.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.RestProductServiceController;
import com.example.demo.entity.Product;
import com.example.demo.interceptor.ProductServiceInterceptor;
import com.example.demo.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RestProductServiceController.class)
public class ProductControllerTest {

	@TestConfiguration
	static class ProductControllerTestContextConfiguration {
		@Bean
		public ProductServiceInterceptor productServiceInterceptor() {
			return new ProductServiceInterceptor();
		}
	}

	@Autowired
	private MockMvc mvc;
	@MockBean
	private ProductService service;

	@Test
	public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {

		Product product = new Product();
		product.setName("name");

		List<Product> allProducts = Arrays.asList(product);

		when(service.getProducts()).thenReturn(allProducts);

		mvc.perform(get("/products/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].name", is(product.getName())));
	}
}
