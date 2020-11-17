package br.com.apirest_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest_springboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
