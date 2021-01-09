package com.example.demo;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.teste.TextService;

//Inicializa contexto completo
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

	@Autowired
	private TextService service;
	
	@org.junit.Test
	public void test() {
		service.someMethod();
	}
}
