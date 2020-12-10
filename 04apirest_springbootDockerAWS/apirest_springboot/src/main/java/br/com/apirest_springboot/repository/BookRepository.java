package br.com.apirest_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest_springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
