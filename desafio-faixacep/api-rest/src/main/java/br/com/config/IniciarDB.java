package br.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.model.FaixaCep;
import br.com.repository.FaixaCepRepository;

@Configuration
public class IniciarDB implements CommandLineRunner{

	@Autowired
	private FaixaCepRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		repository.deleteAll();
		
		FaixaCep model = new FaixaCep();
		model.setCodigoLoja("LOJA_PINHEIROS");
		model.setFaixaInicio(10000000);
		model.setFaixaFim(20000000);
		
		repository.save(model);
	}

}
