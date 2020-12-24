package br.com.apirest_springboot.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest_springboot.dto.AuthorDTO;
import br.com.apirest_springboot.exception.ObjectNotFoundException;
import br.com.apirest_springboot.model.Post;
import br.com.apirest_springboot.model.User;
import br.com.apirest_springboot.repositories.PostRepository;
import br.com.apirest_springboot.repositories.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}

	public List<Post> findByTitle(String text) {
		// return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}

	public List<Post> fullSearch(Date minDate, Date maxDate, String text) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		
		return repository.fullSearch(text, minDate, maxDate);
	}

	public Post create(Post post) {
		User user = userRepository.findById(post.getAuthor().getId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		post.setAuthor(new AuthorDTO(user));
		Post entity = repository.save(post);
		if(entity != null) { 
			user.getPosts().add(entity);
			userRepository.save(user);
		}
		return entity;
	}
	
	public Post addComment(Post post) {
		Post entity = repository.findById(post.getId()).orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
		entity.getComments().addAll(post.getComments());
		return repository.save(entity);
	}
}
