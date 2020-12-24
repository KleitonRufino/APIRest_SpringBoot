package br.com.apirest_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.mongobee.Mongobee;

@Configuration
public class MigrationConfig {

	@Bean
	public Mongobee mongobee(){
	  Mongobee runner = new Mongobee("mongodb://localhost:27017/db00");
	  runner.setDbName("db00");         // host must be set if not set in URI
	  runner.setChangeLogsScanPackage(
	       "br.com.apirest_springboot.migrations"); // the package to be scanned for changesets
	  
	  return runner;
	}
	
}
