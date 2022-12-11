package br.com.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.model.Cliente;
import br.com.repository.ClienteRepository;

@Configuration
public class IniciarDB implements CommandLineRunner {

	@Autowired
	private ClienteRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		repository.deleteAll();

		for (int i = 0; i < 12; i++) {

			Cliente model = new Cliente();
			model.setNascimento(LocalDate.of(1980 + i, i + 1, i + 1));
			model.setNome("joao " + (i + 1));

			repository.save(model);
		}
	}

}
