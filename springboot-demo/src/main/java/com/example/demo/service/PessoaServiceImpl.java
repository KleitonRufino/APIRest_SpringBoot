package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pessoa;
import com.example.demo.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	PessoaRepository repository;
	
	@Override
	public List<Pessoa> listar() {
		return repository.findAll();
	}

	@Override
	public void save(Pessoa pessoa) {
		repository.save(pessoa);
	}

	@Override
	public Pessoa encontrarPorId(int id) {
		return repository.findById(id);
	}

	@Override
	public void remover(int id) {
		repository.delete(repository.findById(id));
	}

}
