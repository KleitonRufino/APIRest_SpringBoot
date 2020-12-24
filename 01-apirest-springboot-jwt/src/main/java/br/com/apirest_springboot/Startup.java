package br.com.apirest_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String result =
		 * encoder.encode("admin123"); System.out.println("My hash " + result);
		 */
	}
}
