package com.skate.skateshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skate.skateshop.model.Producto;
import com.skate.skateshop.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired 
    private ProductoRepository repository;

    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    public void guardarProducto(Producto producto) {



        if (producto.getPrecio() <= 0) {
            throw new RuntimeException(
                    "El precio debe ser mayor a 0");
        }

        if (producto.getStock() < 0) {
            throw new RuntimeException(
                    "El stock no puede ser negativo");
        }

        repository.save(producto);
    }


    public Producto obtenerProducto(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminarProducto(Long id) {
        repository.deleteById(id);
    }
}