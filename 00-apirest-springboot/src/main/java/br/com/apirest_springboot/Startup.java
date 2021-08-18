package br.com.apirest_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import br.com.apirest_springboot.config.FileStorageConfig;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
@EnableAutoConfiguration
@ComponentScan
public class Startup extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Startup.class);
	}
}
