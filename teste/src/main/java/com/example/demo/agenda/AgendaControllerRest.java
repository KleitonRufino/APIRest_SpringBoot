package com.example.demo.agenda;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contatos.Contato;
import com.example.demo.contatos.ContatoService;


@RestController
@RequestMapping("/agenda2")
public class AgendaControllerRest {

	@Autowired
	private ContatoService contatoService;

	@GetMapping("/")
	public ResponseEntity<List<Contato>> contatos(){
		List<Contato> contatos = contatoService.buscarContatos();
		return ResponseEntity.ok(contatos);
	}

	@GetMapping("/contato/{id}")
	public ResponseEntity<Contato> contato(@PathVariable Long id){
		return contatoService.buscarContato(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/inserir")
	public ResponseEntity<Contato> inserir(@RequestBody @Valid Contato contato) {
		contato = contatoService.inserirOuAlterar(contato);
		return new ResponseEntity<>(contato, HttpStatus.CREATED);
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Contato> alterar(@PathVariable Long id, @RequestBody @Valid Contato contato) {
		contato = contatoService.inserirOuAlterar(contato);
		return new ResponseEntity<>(contato, HttpStatus.CREATED);
	}

	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Contato> remover(@PathVariable Long id) {
		contatoService.remover(id);
		return ResponseEntity.noContent().build();
	}

}
