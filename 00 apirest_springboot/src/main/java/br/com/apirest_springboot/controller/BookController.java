package br.com.apirest_springboot.controller;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest_springboot.services.BookServices;
import br.com.apirest_springboot.vo.BookVO;
import io.swagger.annotations.Api;

//@Api(value = "bookEndpoint", description = "REST API for book", tags = { "bookEndpoint" })
@Api(tags = "BookEndpoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookServices service;

	// path params
	// @RequestMapping(method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = service.findAll();
		books.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	// path params
	// @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO BookVO = service.findById(id);
		BookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return BookVO;
	}

	// path params
	// @RequestMapping(method = RequestMethod.POST, consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO BookVO = service.create(book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}

	// path params
	// @RequestMapping(method = RequestMethod.PUT, consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO BookVO = service.update(book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}

	// path params
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
