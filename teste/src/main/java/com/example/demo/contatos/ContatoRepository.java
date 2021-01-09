package com.example.demo.contatos;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.estados.Estado;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	Contato findFirstByNome(String nome);

	List<Contato> findAllByEstado(Estado estado);

	List<Contato> findAllByNomeIgnoreCaseContaining(String nome);

	@Query(value = "SELECT c FROM Contato c")
	List<Contato> buscaTodosContatosComOrdenacao(Sort sort);


	@Query(value = "SELECT c FROM Contato c ORDER BY nome")
	Stream<Contato> buscaTodosContatos();

}
