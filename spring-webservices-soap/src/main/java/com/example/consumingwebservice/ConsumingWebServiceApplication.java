
package com.example.consumingwebservice;

import javax.xml.bind.JAXBException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumingWebServiceApplication {

	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(ConsultarAssinanteClient quoteClient) {
		return args -> {
	
		 quoteClient.getConsultarAssinante();
			
		};
	}

}
