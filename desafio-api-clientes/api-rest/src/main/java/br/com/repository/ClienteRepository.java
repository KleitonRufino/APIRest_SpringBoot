package br.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {


//	@Query("SELECT * FROM Cliente f WHERE" + " :cep BETWEEN f.faixaInicio AND f.faixaFim")
//	List<Cliente> finByCep(@Param("id") int id);

	Optional<Cliente> findById(Long id);

	List<Cliente> findByNome(String nome);
	
}
