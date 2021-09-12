package br.com.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.ProductService;
import br.com.vo.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@Operation(summary = "inserir products")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ProductVO vo) {
		return new ResponseEntity<>(this.service.insert(vo), HttpStatus.CREATED);
	}

	@Operation(summary = "encontrar todos products")
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "encontrar products filtrados")
	@GetMapping(value = "/search", produces = { "application/json" })
	public ResponseEntity<?> search(@RequestParam(name = "q", required = false)  String q, 
			@RequestParam(name = "min_price", required = false) BigDecimal minPrice, 
			@RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
		List<ProductVO> products = this.service.findAll(q, minPrice, maxPrice);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@Operation(summary = "atualizar product")
	@PutMapping(value = "/{id}", produces = { "application/json" })
	public ProductVO put(@PathVariable String id, @RequestBody ProductVO vo) {
		return this.service.put(id, vo);
	}

	@Operation(summary = "encontrar product por id")
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ProductVO findById(@PathVariable String id) {
		return this.service.findById(id);
	}

	@Operation(summary = "deletar product por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
}
