package br.com.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.model.Product;
import br.com.repository.ProductRepository;

@Configuration
public class IniciarDB implements CommandLineRunner{

	@Autowired
	private ProductRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		repository.deleteAll();
		
		for (int i = 0; i < 10; i++) {
			Product model = new Product();
			model.setName("Produto" + i);
			model.setDescription("Description" + i);
			model.setPrice(BigDecimal.valueOf(3.75).multiply(BigDecimal.valueOf(i+1)));
			repository.save(model);
		}
		
	}

}
