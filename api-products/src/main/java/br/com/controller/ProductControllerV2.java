package br.com.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import br.com.vo.ProductHateoasVO;
import br.com.vo.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products com Hateoas")
@RestController
@RequestMapping("/products/v2")
public class ProductControllerV2 {

	@Autowired
	private ProductService service;

	@Operation(summary = "inserir products")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ProductVO vo) {
		ProductHateoasVO hateoasVO = ProductService.getReadVO(this.service.insert(vo));
		hateoasVO.add(linkTo(methodOn(ProductControllerV2.class).findById(hateoasVO.getId())).withSelfRel());
		return new ResponseEntity<>(hateoasVO, HttpStatus.CREATED);
	}

	@Operation(summary = "encontrar todos products")
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<?> findAll() {
		List<ProductHateoasVO> hateoasVO = this.service.findAll().stream().map(o -> ProductService.getReadVO(o))
				.collect(Collectors.toList());
		hateoasVO.forEach(o ->  o.add(linkTo(methodOn(ProductControllerV2.class).findById(o.getId())).withSelfRel()));
		return new ResponseEntity<>(hateoasVO, HttpStatus.OK);
	}
	
	@Operation(summary = "encontrar products filtrados")
	@GetMapping(value = "/search", produces = { "application/json" })
	public ResponseEntity<?> search(@RequestParam(name = "q", required = false)  String q, 
			@RequestParam(name = "min_price", required = false) BigDecimal minPrice, 
			@RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
		List<ProductHateoasVO> hateoasVO = this.service.findAll().stream().map(o -> ProductService.getReadVO(o))
				.collect(Collectors.toList());
		hateoasVO.forEach(o ->  o.add(linkTo(methodOn(ProductControllerV2.class).findById(o.getId())).withSelfRel()));
		return new ResponseEntity<>(hateoasVO, HttpStatus.OK);
	}

	@Operation(summary = "atualizar product")
	@PutMapping(value = "/{id}", produces = { "application/json" })
	public ProductHateoasVO put(@PathVariable String id, @RequestBody @Valid ProductVO vo) {
		ProductHateoasVO hateoasVO = ProductService.getReadVO(this.service.put(id, vo));
		hateoasVO.add(linkTo(methodOn(ProductControllerV2.class).findById(hateoasVO.getId())).withSelfRel());
		return hateoasVO;
	}

	@Operation(summary = "encontrar product por id")
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ProductHateoasVO findById(@PathVariable String id) {
		ProductHateoasVO hateoasVO = ProductService.getReadVO(this.service.findById(id));
		hateoasVO.add(linkTo(methodOn(ProductControllerV2.class).findById(hateoasVO.getId())).withSelfRel());
		return hateoasVO;
	}

	@Operation(summary = "deletar product por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> hateoasVO(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
