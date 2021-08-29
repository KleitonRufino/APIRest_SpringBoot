package br.com.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.FaixaCepService;
import br.com.vo.FaixaCepVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "FaixaCep")
@RestController
@RequestMapping("/api/faixacep/v1")
public class FaixaCepController {

	@Autowired
	private FaixaCepService service;

	@Autowired
	private PagedResourcesAssembler<FaixaCepVO> assembler;

	@ApiOperation(value = "inserir faixa cep para loja")
	@PostMapping
	public FaixaCepVO insert(@RequestBody FaixaCepVO vo) {
		FaixaCepVO response = service.insert(vo);
		return response;
	}

	@ApiOperation(value = "encontrar lojas e suas faixas de cep")
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigoLoja"));

		Page<FaixaCepVO> faixasCep = service.findAll(pageable);
		
		faixasCep.stream().forEach(f -> f.add(linkTo(methodOn(FaixaCepController.class)
		          .findById(f.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(faixasCep);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "encontrar loja fisica para o cep buscado")
	@GetMapping("/{cep}")
	public FaixaCepVO findByCep(@PathVariable int cep) {
		var faixaCepVO = service.findByCep(cep);
		faixaCepVO.add(linkTo(methodOn(FaixaCepController.class).findById(faixaCepVO.getKey())).withSelfRel());
		return faixaCepVO;
	}

	public FaixaCepVO findById(@PathVariable Long id) {
		return service.findById(id);
	}
}
