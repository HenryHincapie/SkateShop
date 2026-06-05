package com.skate.skateshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skate.skateshop.model.Transaccion;

public interface TransaccionRepository
        extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findAllByOrderByIdDesc();
            
}