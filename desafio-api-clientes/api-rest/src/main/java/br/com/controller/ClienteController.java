package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import br.com.service.ClienteService;
import br.com.vo.ClienteVO;
import br.com.vo.ClienteVOInsert;
import br.com.vo.ClienteVOUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente")
@RestController
@RequestMapping("/api/cliente/v1")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Operation(summary = "inserir clientes")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ClienteVOInsert vo) {
		ClienteVO response = service.insert(vo);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "atualizar clientes")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ClienteVOUpdate vo) {
		ClienteVO response = service.update(vo);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "remover clientes")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "encontrar clientes")
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

		Page<ClienteVO> clientes = service.findAll(pageable);

		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@Operation(summary = "encontrar cliente")
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "nome", required = false) String nome) {
		List<ClienteVO> clientes = service.findByIdAndNome(id, nome);

		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	public ClienteVO findById(@PathVariable Long id) {
		return service.findById(id);
	}
}
