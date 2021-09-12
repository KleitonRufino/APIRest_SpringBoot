package br.com.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{


	@Query("SELECT p FROM Product p WHERE" 
			+ "  (UPPER(p.name) LIKE %:q% or  UPPER(p.description) LIKE %:q%) AND (p.price >= :min_price and p.price <= :max_price)")
	List<Product> finByNameOrDescriptionAndMinPriceAndMaxPrice(@Param("q")String s, @Param("min_price")BigDecimal minPrice, @Param("max_price")BigDecimal maxPrice);

	@Query("SELECT p FROM Product p WHERE" 
			+ "  (UPPER(p.name) LIKE %:q% or  UPPER(p.description) LIKE %:q%) AND (p.price >= :min_price)")
	List<Product> finByNameOrDescriptionAndMinPrice(@Param("q")String s, @Param("min_price")BigDecimal minPrice);
	
	@Query("SELECT p FROM Product p WHERE" 
			+ "  (UPPER(p.name) LIKE %:q% or  UPPER(p.description) LIKE %:q%) AND (p.price <= :max_price)")
	List<Product> finByNameOrDescriptionAndMaxPrice(@Param("q")String s, @Param("max_price")BigDecimal maxPrice);
	
	@Query("SELECT p FROM Product p WHERE" 
			+ "  (UPPER(p.name) LIKE %:q% or  UPPER(p.description) LIKE %:q%)")
	List<Product> findByNameOrDescription(@Param("q")String s);
	
	@Query("SELECT p FROM Product p WHERE" 
			+ "  (p.price >= :min_price and p.price <= :max_price)")
	List<Product> finByMinPriceAndMaxPrice(@Param("min_price")BigDecimal minPrice, @Param("max_price")BigDecimal maxPrice);
	
	@Query("SELECT p FROM Product p WHERE" 
			+ "  (p.price >= :min_price)")
	List<Product> finByMinPrice( @Param("min_price")BigDecimal minPrice);
	
	@Query("SELECT p FROM Product p WHERE" 
			+ "  (p.price <= :max_price)")
	List<Product> finByMaxPrice( @Param("max_price")BigDecimal maxPrice);
	

}
