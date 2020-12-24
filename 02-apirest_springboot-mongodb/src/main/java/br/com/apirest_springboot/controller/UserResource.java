package br.com.apirest_springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apirest_springboot.dto.UserDTO;
import br.com.apirest_springboot.model.Post;
import br.com.apirest_springboot.model.User;
import br.com.apirest_springboot.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "User")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	@ApiOperation(value = "Encontrar todos User")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		users.forEach(o -> usersDTO.add(new UserDTO(o)));
		usersDTO.stream().forEach(u -> u.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(UserResource.class).findById(u.getKey())).withSelfRel()));
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Encontrar User pod id")
	public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@GetMapping(value = "/{id}/posts")
	@ApiOperation(value = "Encontrar todos Posts de um User pelo id do User")
	public ResponseEntity<List<Post>> findPosts(@PathVariable(value = "id") String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}

	@PostMapping
	@ApiOperation(value = "Inserir User")
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		user = service.insert(user);
		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri())
				.body(new UserDTO(user));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> upate(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
		userDTO.setKey(id);
		User user = service.fromDTO(userDTO);
		user = service.insert(user);
		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri())
				.body(new UserDTO(user));
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Remover User")
	public ResponseEntity<UserDTO> delete(@PathVariable(value = "id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
