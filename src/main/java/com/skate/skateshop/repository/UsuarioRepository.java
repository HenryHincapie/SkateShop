package com.skate.skateshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skate.skateshop.model.Usuario;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndPassword(
            String email,
            String password);
}