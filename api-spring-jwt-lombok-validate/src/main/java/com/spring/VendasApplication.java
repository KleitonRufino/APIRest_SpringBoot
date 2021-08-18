package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer {
	
	/*
	 * POST http://localhost:8080/api/usuarios {"login":"sa", "senha": "sa"}
	 * POST http://localhost:8080/api/usuarios/auth {"login":"sa", "senha": "sa"}
	 * 
	 * (BEARER TOKEN AUTHORIZATION)
	 * GET http://localhost:8080/api/clientes 
	 * GET http://localhost:8080/api/clientes?nome=a (FILTER)
	 * POST http://localhost:8080/api/clientes {"nome":"sa", "cpf": "01234567890"}
	 * GET http://localhost:8080/api/clientes/{id} 
	 * PUT http://localhost:8080/api/clientes/{id} {"nome":"sa2", "cpf": "01234567890"}
	 * DELETE http://localhost:8080/api/clientes/{id} 
	 */
	public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
