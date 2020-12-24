package br.com.apirest_springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest_springboot.dto.UserDTO;
import br.com.apirest_springboot.exception.ObjectNotFoundException;
import br.com.apirest_springboot.model.User;
import br.com.apirest_springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repository.save(user);
	}

	public User update(User user) {
		User entity = findById(user.getId());
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		return repository.save(entity);
	}
	
	public void delete(String id) {
		User user = findById(id);
		repository.delete(user);
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getKey(), userDTO.getName(), userDTO.getEmail());
	}
}
