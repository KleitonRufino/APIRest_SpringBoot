package br.com.apirest_springboot.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest_springboot.exception.ObjectNotFoundException;
import br.com.apirest_springboot.model.Post;
import br.com.apirest_springboot.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle(String text) {
		// return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}

	public List<Post> fullSearch(Date minDate, Date maxDate, String text) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		
		return repository.fullSearch(text, minDate, maxDate);
	}

}
