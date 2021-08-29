package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.model.FaixaCep;

public interface FaixaCepRepository extends JpaRepository<FaixaCep, Long>{

	@Query("SELECT f FROM FaixaCep f WHERE"
			+ " (:faixa_inicio BETWEEN f.faixaInicio AND f.faixaFim)"
			+ " OR (:faixa_fim BETWEEN f.faixaInicio AND f.faixaFim)"
			+ " OR (f.faixaFim BETWEEN :faixa_fim AND :faixa_inicio)"
			+ " OR (f.faixaInicio BETWEEN :faixa_inicio AND :faixa_fim)")
	FaixaCep availableRange(@Param("faixa_inicio") int faixaInicio, @Param("faixa_fim") int faixaFim);
	
	@Query("SELECT f FROM FaixaCep f WHERE" 
			+ " :cep BETWEEN f.faixaInicio AND f.faixaFim")
	FaixaCep finByCep(@Param("cep")int cep);
}
