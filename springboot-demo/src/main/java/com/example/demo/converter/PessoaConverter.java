package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Pessoa;
import com.example.demo.model.PessoaModel;


@Component
public class PessoaConverter {

	

	// Entity --> Model
	public PessoaModel entity2model(Pessoa pessoa) {
		return new PessoaModel(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone());
	}

	// Model --> Entity
	public Pessoa model2entity(PessoaModel model) {
		return new Pessoa(model.getId(), model.getNome(), model.getEmail(), model.getTelefone());
	}
}
