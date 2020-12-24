package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Serializable>{
	
	Pessoa findById(int id);
}
