package br.com.apirest_springboot.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//http://localhost:8080/v2/api-docs
	//http://localhost:8080/swagger-ui.html
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.apirest_springboot")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Simple RESTful API With Spring Boot 2", "", "v1",
				null, new Contact("Kleiton Rufino", "https://github.com/KleitonRufino", "kleiton.arufino@gmail.com"),
				"Código API no github", "https://github.com/KleitonRufino/APIRest_SpringBoot", Collections.emptyList());
	}
}