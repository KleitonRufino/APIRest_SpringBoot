package com.example.demo.contatos;

import java.util.List;
import java.util.Optional;

public interface ContatoService {

	void inserir(Contato contato) throws ContatoException;

	void remover(Long id);
	

	List<Contato> buscarContatos();

	Optional<Contato> buscarContato(Long id);

	Contato inserirOuAlterar(Contato contato);

	
}
