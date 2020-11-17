package br.com.apirest_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest_springboot.services.PersonServices;
import br.com.apirest_springboot.vo.v2.PersonVOV2;

@RestController
@RequestMapping("/api/person/v2")
public class PersonControllerV2 {

	@Autowired
	private PersonServices service;

	@PostMapping
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}
}
