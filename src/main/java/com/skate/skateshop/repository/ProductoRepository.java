package com.skate.skateshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skate.skateshop.model.Producto;

public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

}