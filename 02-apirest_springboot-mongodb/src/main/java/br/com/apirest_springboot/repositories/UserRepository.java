package br.com.apirest_springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest_springboot.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
