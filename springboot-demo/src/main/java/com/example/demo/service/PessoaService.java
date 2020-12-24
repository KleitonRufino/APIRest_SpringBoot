package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Pessoa;

public interface PessoaService {

	List<Pessoa> listar();
	void save(Pessoa pessoa);
	Pessoa encontrarPorId(int id);
	void remover(int id);
}
