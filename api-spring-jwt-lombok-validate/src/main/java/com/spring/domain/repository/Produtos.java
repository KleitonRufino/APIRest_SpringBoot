package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
