package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
