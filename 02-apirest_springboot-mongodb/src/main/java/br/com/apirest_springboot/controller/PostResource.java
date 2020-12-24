package br.com.apirest_springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest_springboot.model.Post;
import br.com.apirest_springboot.services.PostService;
import br.com.apirest_springboot.utils.URLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/posts")
@Api(tags = "Post")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Encontrar Post por id")
	public ResponseEntity<Post> findById(@PathVariable(value = "id") String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/titlesearch")
	@ApiOperation(value = "Encontrar Post por Titulo")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text") String text) {
		text = URLUtil.decodeParam(text);
		List<Post> post = service.findByTitle(text);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/fullsearch")
	@ApiOperation(value = "Encontrar Post por texto e intervalo de datas")
	public ResponseEntity<List<Post>> fullsearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URLUtil.decodeParam(text);
		Date minDate2 = URLUtil.convertDate(minDate, new Date(0L));
		Date maxDate2 = URLUtil.convertDate(maxDate, new Date());

		List<Post> post = service.fullSearch(minDate2, maxDate2, text);
		return ResponseEntity.ok().body(post);
	}
	
	@PostMapping()
	@ApiOperation(value = "Adicionar Post por id do User")
	public ResponseEntity<Post> create(@RequestBody Post post){
		Post result = service.create(post);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping(value = "/addComentario")
	@ApiOperation(value = "Adicionar comentário ao Post")
	public ResponseEntity<Post> addComment(@RequestBody Post post){
		Post result = service.addComment(post);
		return ResponseEntity.ok().body(result);
	}
}
