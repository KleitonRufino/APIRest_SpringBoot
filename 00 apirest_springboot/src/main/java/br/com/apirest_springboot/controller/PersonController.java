package br.com.apirest_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest_springboot.services.PersonServices;
import br.com.apirest_springboot.vo.PersonVO;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices service;

	// path params
	// @RequestMapping(method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	// path params
	// @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	// path params
	// @RequestMapping(method = RequestMethod.POST, consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

//	@PostMapping("/v2")
//	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
//		return service.createV2(person);
//	}

	// path params
	// @RequestMapping(method = RequestMethod.PUT, consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		return service.create(person);
	}

	// path params
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
