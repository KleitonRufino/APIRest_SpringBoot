package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter{


	/*
	 * Você pode usar o Interceptor no Spring Boot para realizar operações sob o
	 * seguinte situações:
	 *   Antes de enviar a solicitação ao controlador
	 *    Antes de enviar a resposta ao cliente
	 */
	
	@Autowired
	ProductServiceInterceptor productServiceInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productServiceInterceptor);
	}
}
