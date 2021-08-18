package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
