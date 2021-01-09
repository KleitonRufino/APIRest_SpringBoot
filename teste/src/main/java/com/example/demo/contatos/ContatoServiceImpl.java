package com.example.demo.contatos;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void inserir(Contato contato) throws ContatoException {
		try {
			contatoRepository.save(contato);
		} catch (ConstraintViolationException e) {
			throw new ContatoException(e);
		}
	}

	@Override
	public void remover(Long id) {
		contatoRepository.deleteById(id);
	}

	@Override
	public List<Contato> buscarContatos() {
		return contatoRepository.findAll();
	}
	@Override
	public Optional<Contato> buscarContato(Long id) {
		return contatoRepository.findById(id);
	}


	@Override
	public Contato inserirOuAlterar(Contato contato) {
		return contatoRepository.save(contato);
	}
}
